package Service;

import java.sql.*;

public class DatabaseConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/Konvikti";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345678";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found", e);
        }
    }

    public static boolean insertLoginData(String email, String password, String userType) {
        String query = "INSERT INTO login(email, password, user_type) VALUES(?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            statement.setString(2, PasswordHasher.hashPassword(password));
            statement.setString(3, userType);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting login data: " + e.getMessage());
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

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting signup data: " + e.getMessage());
            return false;
        }
    }

    public static boolean validateLogin(String email, String password, String userType) {
        String sql = "SELECT password FROM signup WHERE email = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHashedPassword = rs.getString("password");
                    // The password checking logic remains the same.
                    return PasswordHasher.checkPassword(password, storedHashedPassword);
                }
            }
        } catch (SQLException e) {
            System.err.println("Login validation error: " + e.getMessage());
        }
        return false;
    }

    public static boolean checkEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM signup WHERE email = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Email check error: " + e.getMessage());
        }
        return false;
    }

    public static boolean insertForgotPasswordData(String email, int confirmationCode, String newPassword) {
        String query = "INSERT INTO forgotPassword(email, confirmationCode, newPassword) VALUES(?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            statement.setInt(2, confirmationCode);
            statement.setString(3, PasswordHasher.hashPassword(newPassword));

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting forgot password data: " + e.getMessage());
            return false;
        }
    }

    public static boolean updatePassword(String email, String newPassword) {
        String query = "UPDATE signup SET password = ? WHERE email = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, PasswordHasher.hashPassword(newPassword));
            statement.setString(2, email);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating password: " + e.getMessage());
            return false;
        }
    }

    public static DatabaseConnector getInstance() {
        return null;
    }
}