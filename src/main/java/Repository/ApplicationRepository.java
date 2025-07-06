package Repository;

import Model.Application;
import Model.Application.Status;
import Model.Student;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApplicationRepository {

    private Connection connection;

    public ApplicationRepository(Connection connection) {
        this.connection = connection;
    }

    public void addApplication(Application application) throws SQLException {
        String sql = "INSERT INTO applications (application_date, student_id, status, deadline) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(application.getApplicationDate()));
            stmt.setInt(2, application.getStudent().getId());
            stmt.setString(3, application.getStatus().name());
            stmt.setDate(4, Date.valueOf(application.getDeadline()));
            stmt.executeUpdate();
        }
    }

    public List<Application> getAllApplications() throws SQLException {
        List<Application> applications = new ArrayList<>();
        String sql = "SELECT a.*, s.id as s_id, s.first_name, s.last_name FROM applications a JOIN students s ON a.student_id = s.id";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("s_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );

                Application application = new Application(
                        rs.getDate("application_date").toLocalDate(),
                        student,
                        Status.valueOf(rs.getString("status")),
                        rs.getDate("deadline").toLocalDate()
                );

                applications.add(application);
            }
        }
        return applications;
    }

    public boolean deleteApplication(LocalDate applicationDate, int studentId) throws SQLException {
        String sql = "DELETE FROM applications WHERE application_date = ? AND student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(applicationDate));
            stmt.setInt(2, studentId);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean updateStatus(int studentId, LocalDate applicationDate, Status status) throws SQLException {
        String sql = "UPDATE applications SET status = ? WHERE student_id = ? AND application_date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status.name());
            stmt.setInt(2, studentId);
            stmt.setDate(3, Date.valueOf(applicationDate));
            return stmt.executeUpdate() > 0;
        }
    }
}
