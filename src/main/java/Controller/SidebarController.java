package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SidebarController {

    private void switchScene(ActionEvent event, String fxmlPath, String title) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(view));
        stage.setTitle(title);
    }

    public void goDashboard(ActionEvent event) throws IOException {
        switchScene(event, "/App/homeDashboard.fxml", "DMS Dashboard");
    }

    public void goStudents(ActionEvent event) throws IOException {
        switchScene(event, "/App/studentsView.fxml", "Studentët");
    }

    public void goRooms(ActionEvent event) throws IOException {
        switchScene(event, "/App/roomsView.fxml", "Dhomat");
    }

    public void goApplications(ActionEvent event) throws IOException {
        switchScene(event, "/App/applicationsView.fxml", "Aplikimet");
    }

    public void goStats(ActionEvent event) throws IOException {
        switchScene(event, "/App/statisticsView.fxml", "Statistikat");
    }

    public void goHelp(ActionEvent event) throws IOException {
        switchScene(event, "/App/helpView.fxml", "Ndihma");
    }
}
