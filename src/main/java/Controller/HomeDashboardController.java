package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

public class HomeDashboardController {

    @FXML
    private Button accountButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button applyButton;

    @FXML
    private Button searchStudentButton;

    @FXML
    private Button addRoomButton;

    @FXML
    private Label statusLabel;

    public void initialize() {
        statusLabel.setText("Gati | Admin i kyçur");
    }

    private void switchScene(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();
            Stage stage = (Stage) statusLabel.getScene().getWindow();
            Scene scene = new Scene(view, 1000, 700);
            scene.getStylesheets().add(getClass().getResource("/App/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAccountButton() {
        statusLabel.setText("Llogaria e përdoruesit");
        // Add account view if needed
    }

    @FXML
    private void handleLogoutButton() {
        statusLabel.setText("Duke dalë nga llogaria...");
        switchScene("/App/loginApplication.fxml", "Login Form");
    }

    @FXML
    private void handleApplyButton() {
        statusLabel.setText("Forma e aplikimit u hap.");
        switchScene("/App/applicationsView.fxml", "Aplikimet");
    }

    @FXML
    private void handleSearchStudentButton() {
        statusLabel.setText("Dritarja për kërkim studenti u hap.");
        switchScene("/App/studentsView.fxml", "Studentët");
    }

    @FXML
    private void handleAddRoomButton() {
        statusLabel.setText("Shto një dhomë të re.");
        switchScene("/App/roomsView.fxml", "Dhomat");
    }

    @FXML
    private void handleDashboardClick() {
        statusLabel.setText("Dashboard u klikua");
        switchScene("/App/homeDashboard.fxml", "DMS Dashboard");
    }

    @FXML
    private void handleStudentsClick() {
        statusLabel.setText("Studentët u klikuan");
        switchScene("/App/studentsView.fxml", "Studentët");
    }

    @FXML
    private void handleRoomsClick() {
        statusLabel.setText("Dhomat u klikuan");
        switchScene("/App/roomsView.fxml", "Dhomat");
    }

    @FXML
    private void handleApplicationsClick() {
        statusLabel.setText("Aplikimet u klikuan");
        switchScene("/App/applicationsView.fxml", "Aplikimet");
    }

    @FXML
    private void handleStatsClick() {
        statusLabel.setText("Statistikat u klikuan");
        switchScene("/App/statisticsView.fxml", "Statistikat");
    }

    @FXML
    private void handleHelpClick() {
        statusLabel.setText("Ndihma u klikua");
        switchScene("/App/helpView.fxml", "Ndihmë");
    }
}