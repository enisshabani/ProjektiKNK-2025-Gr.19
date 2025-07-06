package Repository;

import Model.DTO.AplikimetDTO;
import Service.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatistikaRepositoryImpl implements StatistikaRepository {

    @Override
    public List<AplikimetDTO> getAplikimet() {
        List<AplikimetDTO> aplikimet = new ArrayList<>();
        String query = "SELECT id, studentName, applicationDate, status FROM aplikimet";
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
}
