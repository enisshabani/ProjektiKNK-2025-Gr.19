package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TopbarController {

    @FXML
    private Button logoutButton;

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/App/loginApplication.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) logoutButton.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button accountButton;

    @FXML
    private void handleAccount(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/App/studentsView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) accountButton.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Students");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
