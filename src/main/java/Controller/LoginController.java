package Controller;

import Database.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
}