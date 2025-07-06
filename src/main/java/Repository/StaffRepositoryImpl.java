package Repository;

import Model.Building;
import Model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffRepositoryImpl implements StaffRepository {
    private final Connection connection;

    public StaffRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Staff staff) {
        String sql = "INSERT INTO staff (first_name, last_name, position, shift, building_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, staff.getFirstName());
            stmt.setString(2, staff.getLastName());
            stmt.setString(3, staff.getPosition().name());
            stmt.setString(4, staff.getShift().name());
            stmt.setInt(5, staff.getBuilding() != null ? staff.getBuilding().getId() : null);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Staff findById(int id) {
        String sql = "SELECT * FROM staff WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Staff> findAll() {
        String sql = "SELECT * FROM staff";
        List<Staff> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Staff staff) {
        String sql = "UPDATE staff SET first_name = ?, last_name = ?, position = ?, shift = ?, building_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, staff.getFirstName());
            stmt.setString(2, staff.getLastName());
            stmt.setString(3, staff.getPosition().name());
            stmt.setString(4, staff.getShift().name());
            stmt.setInt(5, staff.getBuilding() != null ? staff.getBuilding().getId() : null);
            stmt.setInt(6, staff.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM staff WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Staff> findByBuildingId(int buildingId) {
        String sql = "SELECT * FROM staff WHERE building_id = ?";
        List<Staff> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, buildingId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Staff mapResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        Staff.Position position = Staff.Position.valueOf(rs.getString("position"));
        Staff.Shift shift = Staff.Shift.valueOf(rs.getString("shift"));

        // Building
        Building building = null;
        int buildingId = rs.getInt("building_id");
        if (!rs.wasNull()) {
            building = new Building(buildingId);
        }

        return new Staff(id, firstName, lastName, position, shift, building);
    }
}
