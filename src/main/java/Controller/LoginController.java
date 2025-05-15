package Controller;

import Database.DatabaseConnector;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;

    // Initialize method (optional)
    @FXML
    public void initialize() {
        // Set button action if not set in Scene Builder
        loginButton.setOnAction(this::handleLoginButtonAction);
    }

    // Handle login button click
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        // Input validation
        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Input Error", "Email and password cannot be empty!");
            return;
        }

        try {
            // Insert data into database
            boolean success = DatabaseConnector.insertLoginData(email, password);

            if (success) {
                showAlert("Success", "Login data saved to database!");
                clearFields();
                System.out.println("Data inserted: " + email);

                // Redirect to homeDashboard.fxml
                Parent root = FXMLLoader.load(getClass().getResource("/App/homeDashboard.fxml"));
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                showAlert("Error", "Failed to save login data!");
            }
        } catch (Exception e) {
            showAlert("Database Error", "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Clear input fields
    private void clearFields() {
        emailField.clear();
        passwordField.clear();
    }

    private void handleSuccessfulLogin() {

        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished(event -> {
            try {
                // Load the home dashboard
                Parent root = FXMLLoader.load(getClass().getResource("/App/homeDashboard.fxml"));
                Scene scene = loginButton.getScene();
                scene.setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        delay.play();
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        boolean loginSuccessful = validateLogin(); // Your validation method

        if (loginSuccessful) {
            try {
                // 2. Load the home dashboard
                Parent root = FXMLLoader.load(getClass().getResource("/App/homeDashboard.fxml"));

                // 3. Get the current stage
                Stage stage = (Stage) loginButton.getScene().getWindow();

                // 4. Set the new scene
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid credentials").show();
        }
    }

    private boolean validateLogin() {
        return false;
    }

    @FXML
    private void handleSignUpLink(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/App/signupApplication.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Could not load the sign-up page");
        }
    }

    @FXML
    private void handleForgotPasswordLink(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/App/forgotPassword.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}