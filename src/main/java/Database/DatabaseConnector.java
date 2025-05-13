package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/konvikti";
    private static final String USER = "postgres";
    private static final String databasePassword = "12345678";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            // Connection with postgres user and no password
            return DriverManager.getConnection(URL, USER, databasePassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found", e);
        }
    }

    public static boolean insertLoginData(String email, String password) {
        String query = "INSERT INTO login(email, password) VALUES(?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            statement.setString(2, password);
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error inserting login data: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            return false;
        }
    }
}