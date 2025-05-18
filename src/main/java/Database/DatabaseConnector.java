package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/Konvikti";
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

    public static boolean insertLoginData(String email, String password, String user_type) {
        String query = "INSERT INTO login(email, password, user_type) VALUES(?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, user_type);
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error inserting login data: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            return false;
        }
    }
    public static boolean insertSignupData(String name, String lastname, String email,
                                           String password, String university,
                                           String universityYear, double averageGrade,
                                           String gender) {
        String query = "INSERT INTO signup(name, lastname, email, password, university, " +
                "universityYear, averageGrade, gender) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            statement.setString(2, lastname);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, university);
            statement.setString(6, universityYear);
            statement.setDouble(7, averageGrade);
            statement.setString(8, gender);

            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error inserting signup data: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            return false;
        }
    }

    public static boolean insertForgotPasswordData(String email, int confirmationCode, String newPassword) {
        String query = "INSERT INTO forgotPassword(email, confirmationCode, newPassword) VALUES(?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            statement.setInt(2, confirmationCode);
            statement.setString(3, newPassword);
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error inserting forgot password data: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            return false;
        }
    }

    public static boolean updatePassword(String email, String newPassword) {
        String query = "UPDATE signup SET password = ? WHERE email = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newPassword);
            statement.setString(2, email);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating password: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            return false;
        }
    }
}