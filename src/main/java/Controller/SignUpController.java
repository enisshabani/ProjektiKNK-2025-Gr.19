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
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signupButton;

    @FXML
    private Hyperlink loginLink;

    @FXML
    private Hyperlink helpLink;

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        String name = nameField.getText();
        String lastname = lastnameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validate input fields
        if (name.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("Please fill in all fields");
            return;
        }

        System.out.println("Signing up user:");
        System.out.println("Name: " + name);
        System.out.println("Lastname: " + lastname);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        try {
            // Load the login FXML file
            Parent root = FXMLLoader.load(getClass().getResource("/App/loginApplication.fxml"));

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
//            showAlert("Navigation Error", "Could not load the login page");
        }
    }
}