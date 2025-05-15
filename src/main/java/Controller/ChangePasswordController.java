package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangePasswordController {

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField repeatPasswordField;

    @FXML
    private Button continueButton;

    @FXML
    private Hyperlink helpLink;

    @FXML
    private Hyperlink noAccount;

    @FXML
    private void initialize() {
        // Initialization code if needed
    }

    @FXML
    private void handleContinueButton(ActionEvent event) {
        String newPassword = newPasswordField.getText();
        String repeatedPassword = repeatPasswordField.getText();

        // Validate inputs
        if (newPassword.isEmpty() || repeatedPassword.isEmpty()) {
            showAlert("Input Error", "Please fill in both password fields");
            return;
        }

        if (!newPassword.equals(repeatedPassword)) {
            showAlert("Input Error", "Passwords do not match");
            return;
        }

        if (newPassword.length() < 8) {
            showAlert("Input Error", "Password must be at least 8 characters long");
            return;
        }

        // Process password change (in a real app, this would update the database)
        System.out.println("Password changed successfully");
        showAlert("Success", "Your password has been changed successfully");

        // Optionally redirect to login page after successful password change
        redirectToLogin(event);
    }

    @FXML
    private void handleNoAccountLink(ActionEvent event) {
        try {
            // Load the signup FXML file
            Parent root = FXMLLoader.load(getClass().getResource("/App/signupApplication.fxml"));

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Could not load the signup page");
        }
    }

    private void redirectToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/App/loginApplication.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Could not load the login page");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}