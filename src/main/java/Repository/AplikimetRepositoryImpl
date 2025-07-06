package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.DTO.AplikimetDTO;
import Service.DatabaseConnector;

public class AplikimetRepositoryImpl implements AplikimetRepository {
@Override
    public List<AplikimetDTO> getAllAplikimet() {
        List<AplikimetDTO> aplikimet = new ArrayList<>();
        String query = "SELECT * FROM aplikimet"; // Assuming you have a table named 'aplikimet'
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                aplikimet.add(new AplikimetDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("studentName"),
                        resultSet.getDate("applicationDate").toLocalDate(),
                        resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aplikimet;
    }

    @Override
    public void updateAplikimStatus(int aplikimId, String newStatus) {
        String query = "UPDATE aplikimet SET status = ? WHERE id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newStatus);
            statement.setInt(2, aplikimId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
