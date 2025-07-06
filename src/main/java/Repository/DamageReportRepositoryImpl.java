package Repository;

import Model.DamageReport;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DamageReportRepositoryImpl implements DamageReportRepository {
    private final Connection connection;

    public DamageReportRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(DamageReport damageReport) {
        String sql = "INSERT INTO damage_reports (description, report_date, reporter, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, damageReport.getDescription());
            stmt.setDate(2, Date.valueOf(damageReport.getReportDate()));
            stmt.setString(3, damageReport.getReporter());
            stmt.setString(4, damageReport.getStatus().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DamageReport findById(int id) {
        String sql = "SELECT * FROM damage_reports WHERE id = ?";
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
    public List<DamageReport> findAll() {
        String sql = "SELECT * FROM damage_reports";
        List<DamageReport> reports = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                reports.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public List<DamageReport> findByReporter(String reporter) {
        String sql = "SELECT * FROM damage_reports WHERE reporter = ?";
        List<DamageReport> reports = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, reporter);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reports.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public void updateStatus(int id, DamageReport.Status status) {
        String sql = "UPDATE damage_reports SET status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status.name());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DamageReport mapResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String description = rs.getString("description");
        LocalDate reportDate = rs.getDate("report_date").toLocalDate();
        String reporter = rs.getString("reporter");
        DamageReport.Status status = DamageReport.Status.valueOf(rs.getString("status"));
        return new DamageReport(id, description, reportDate, reporter, status);
    }
}
