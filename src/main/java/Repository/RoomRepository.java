package Repository;

import Model.DTO.RoomDTO;
import Service.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {
    private final DatabaseConnector databaseConnector;

    public RoomRepository() {
        this.databaseConnector = DatabaseConnector.getInstance();
    }

    public boolean roomNumberExists(String roomNumber) {
        String query = "SELECT COUNT(*) FROM rooms WHERE room_number = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roomNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking room number: " + e.getMessage());
            return false;
        }
    }

    public boolean roomExists(int roomId) {
        String query = "SELECT COUNT(*) FROM rooms WHERE id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, roomId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking room existence: " + e.getMessage());
            return false;
        }
    }

    public List<RoomDTO> getAllRooms() {
        List<RoomDTO> rooms = new ArrayList<>();
        String query = "SELECT r.id, s.name as first_name, s.lastname as last_name, " +
                "r.room_number, COALESCE(s.gender, '') as gender, " +
                "r.status as room_status, r.building, r.capacity " +
                "FROM rooms r LEFT JOIN signup s ON r.student_id = s.id";

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                rooms.add(new RoomDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("room_number"),
                        resultSet.getString("gender"),
                        resultSet.getString("room_status"),
                        resultSet.getString("building"),
                        resultSet.getInt("capacity")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching rooms: " + e.getMessage());
        }
        return rooms;
    }

    public boolean addRoom(RoomDTO roomDTO) {
        String query = "INSERT INTO rooms (room_number, building, capacity, status) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roomDTO.getRoomNumber());
            statement.setString(2, roomDTO.getBuilding());
            statement.setInt(3, roomDTO.getCapacity());
            statement.setString(4, roomDTO.getRoomStatus());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding room: " + e.getMessage());
            return false;
        }
    }

    public boolean updateRoom(RoomDTO roomDTO) {
        String query = "UPDATE rooms SET room_number=?, building=?, capacity=?, status=? WHERE id=?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roomDTO.getRoomNumber());
            statement.setString(2, roomDTO.getBuilding());
            statement.setInt(3, roomDTO.getCapacity());
            statement.setString(4, roomDTO.getRoomStatus());
            statement.setInt(5, roomDTO.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating room: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteRoom(int roomId) {
        String query = "DELETE FROM rooms WHERE id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, roomId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting room: " + e.getMessage());
            return false;
        }
    }
}