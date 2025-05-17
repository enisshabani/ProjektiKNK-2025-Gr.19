package Controller;

import Database.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.MissingResourceException;
import java.io.IOException;

public class ForgotPasswordController {

    @FXML private AnchorPane forgotPasswordLink;
    @FXML private TextField emailField;
    @FXML private TextField confirmationField;
    @FXML private Hyperlink helpLink;
    @FXML private Hyperlink aboutLink;
    @FXML private Hyperlink signupLink;
    @FXML private Label newPasswordLabel;
    @FXML private Button continueButton;
    @FXML private Label confirmationLabel;
    @FXML private Hyperlink noAccountLink;
    @FXML private Button languageButton;
    @FXML private TextField newPasswordField;

    private ResourceBundle bundle;
    private boolean isAlbanian = false;

    @FXML
    private void initialize() {
        loadLanguage(false); // Load default (English)
    }

    @FXML
    private void handleContinueButtonAction() {
        String email = emailField.getText();
        String confirmationCode = confirmationField.getText();

        System.out.println("Email: " + email);
        System.out.println("Confirmation Code: " + confirmationCode);
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
            showAlert("Navigation Error", "Could not load the signup page");
        }
    }

    @FXML
    private void handleLanguageSwitch() {
        isAlbanian = !isAlbanian; // Toggle language
        loadLanguage(isAlbanian);
    }

    private void loadLanguage(boolean isAlbanian) {
        try {
            Locale locale = isAlbanian ? new Locale("al") : Locale.ENGLISH;
            bundle = ResourceBundle.getBundle("language/strings", locale);

            confirmationLabel.setText(bundle.getString("confirmationLabel"));
            newPasswordLabel.setText(bundle.getString("newPasswordLabel"));
            noAccountLink.setText(bundle.getString("noAccountLink"));
            continueButton.setText(bundle.getString("continueButton"));
            helpLink.setText(bundle.getString("helpLink"));
            aboutLink.setText(bundle.getString("aboutLink"));
            languageButton.setText(isAlbanian ? "EN | AL" : "AL | EN");

        } catch (MissingResourceException e) {
            e.printStackTrace();
            showAlert("Error", "Language resource files not found or keys missing!");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleContinueButton(ActionEvent event) {
        String email = emailField.getText();
        String confirmationCode = confirmationField.getText();
        String newPassword = newPasswordField.getText();

        if (email.isEmpty() || confirmationCode.isEmpty() || newPassword.isEmpty()) {
            showAlert("Error", "Please fill in all fields");
            return;
        }

        try {
            int code = Integer.parseInt(confirmationCode);

            boolean requestSaved = DatabaseConnector.insertForgotPasswordData(email, code, newPassword);

            if (requestSaved) {
                boolean passwordUpdated = DatabaseConnector.updatePassword(email, newPassword);

                if (passwordUpdated) {
                    showAlert("Success", "Password updated successfully!");
                    redirectToLogin(event);
                } else {
                    showAlert("Error", "Failed to update password. Email not found.");
                }
            } else {
                showAlert("Error", "Failed to process password reset request");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Confirmation code must be a number");
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
}