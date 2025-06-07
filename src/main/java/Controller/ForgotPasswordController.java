package Controller;

import Model.DTO.ForgotPasswordDTO;
import Service.ForgotPasswordService;
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
    private ForgotPasswordService forgotPasswordService;

    @FXML
    private void initialize() {
        loadLanguage(false);
        helpLink.setOnAction(this::handleHelpLink);
        confirmationField.setPromptText("6-digit code");
        this.forgotPasswordService = new ForgotPasswordService(bundle);
    }

    @FXML
    private void handleHelpLink(ActionEvent event) {
        navigateToPage(event, "/App/helpView.fxml", "help page");
    }

    @FXML
    private void handleSignUpLink(ActionEvent event) {
        navigateToPage(event, "/App/signupApplication.fxml", "signup page");
    }

    @FXML
    private void handleLanguageSwitch() {
        isAlbanian = !isAlbanian;
        loadLanguage(isAlbanian);
        // Reinitialize service with updated bundle
        this.forgotPasswordService = new ForgotPasswordService(bundle);
    }

    private void loadLanguage(boolean isAlbanian) {
        try {
            Locale locale = isAlbanian ? new Locale("al") : Locale.ENGLISH;
            bundle = ResourceBundle.getBundle("language/strings", locale);

            // Update UI elements with translated text
            confirmationLabel.setText(bundle.getString("confirmationLabel"));
            newPasswordLabel.setText(bundle.getString("newPasswordLabel"));
            noAccountLink.setText(bundle.getString("noAccountLink"));
            continueButton.setText(bundle.getString("continueButton"));
            helpLink.setText(bundle.getString("helpLink"));
            aboutLink.setText(bundle.getString("aboutLink"));
            confirmationField.setPromptText(bundle.getString("confirmationFieldPrompt"));
            languageButton.setText(isAlbanian ? "EN | AL" : "AL | EN");
            emailField.setPromptText(bundle.getString("emailPrompt"));

        } catch (MissingResourceException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleContinueButton(ActionEvent event) {
        String email = emailField.getText().trim();
        String confirmationCode = confirmationField.getText().trim();
        String newPassword = newPasswordField.getText().trim();

        // Validate input fields
        if (email.isEmpty() || confirmationCode.isEmpty() || newPassword.isEmpty()) {
            showAlert("Error", bundle.getString("fillAllFields"));
            return;
        }

        // Validate confirmation code format
        if (!confirmationCode.matches("\\d{6}")) {
            showAlert("Error", bundle.getString("invalidConfirmationCode"));
            return;
        }

        try {
            int code = Integer.parseInt(confirmationCode);
            ForgotPasswordDTO dto = new ForgotPasswordDTO(email, code, newPassword);

            String result = forgotPasswordService.processPasswordReset(dto);

            if ("success".equals(result)) {
                showAlert("Success", bundle.getString("passwordUpdatedSuccess"));
                redirectToLogin(event);
            } else {
                showAlert("Error", result);
            }
        } catch (NumberFormatException e) {
            showAlert("Error", bundle.getString("invalidConfirmationCode"));
        }
    }

    private void navigateToPage(ActionEvent event, String fxmlPath, String pageName) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Could not load the " + pageName);
        }
    }

    private void redirectToLogin(ActionEvent event) {
        navigateToPage(event, "/App/loginApplication.fxml", "login page");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}