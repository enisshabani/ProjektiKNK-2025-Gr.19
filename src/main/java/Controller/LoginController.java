package Controller;

import Database.DatabaseConnector;
import Service.PasswordHasher;
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
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ResourceBundle;
import java.util.Locale;
import java.util.MissingResourceException;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Hyperlink noAccountLink;
    @FXML
    private Hyperlink forgotPasswordLink;
    @FXML
    private Hyperlink signupLink;
    @FXML
    private Hyperlink helpLink;
    @FXML
    private Hyperlink aboutLink;
    @FXML
    private MenuButton userTypeMenuButton;
    @FXML
    private MenuItem studentMenuItem;
    @FXML
    private MenuItem adminMenuItem;
    @FXML
    private Button languageButton;

    private ResourceBundle bundle;
    private boolean isAlbanian = false;

    private String selectedUserType;

    @FXML
    public void initialize() {
        loadLanguage(false);
        loginButton.setOnAction(this::handleLoginButtonAction);
        helpLink.setOnAction(this::handleHelpLink);

        selectedUserType = "Student";
        userTypeMenuButton.setText(bundle != null ? bundle.getString("studentMenuItem") : "Student");
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String user_type = selectedUserType;

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Input Error", "Email and password cannot be empty!");
            return;
        }

        try {
            // Hash the password before storing it
            String hashedPassword = PasswordHasher.hashPassword(password);
            boolean success = DatabaseConnector.insertLoginData(email, hashedPassword, user_type);

            if (success) {
                showAlert("Success", "Login data saved to database!");
                clearFields();
                System.out.println("Data inserted: " + email);

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

    @FXML
    private void handleHelpLink(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/App/helpView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Could not load the help page");
        }
    }

    @FXML
    private void handleLanguageSwitch(ActionEvent event) {
        isAlbanian = !isAlbanian;
        loadLanguage(isAlbanian);
    }

    private void loadLanguage(boolean isAlbanian) {
        try {
            Locale locale = isAlbanian ? new Locale("al") : Locale.ENGLISH;
            bundle = ResourceBundle.getBundle("language/strings", locale);

            loginButton.setText(bundle.getString("loginButton"));
            passwordLabel.setText(bundle.getString("passwordLabel"));
            noAccountLink.setText(bundle.getString("noAccountLink"));
            forgotPasswordLink.setText(bundle.getString("forgotPasswordLink"));
            signupLink.setText(bundle.getString("signupLink"));
            helpLink.setText(bundle.getString("helpLink"));
            aboutLink.setText(bundle.getString("aboutLink"));
            userTypeMenuButton.setText(bundle.getString("selectUser"));
            studentMenuItem.setText(bundle.getString("studentMenuItem"));
            adminMenuItem.setText(bundle.getString("adminMenuItem"));
            languageButton.setText(isAlbanian ? "EN | AL" : "AL | EN");

        } catch (MissingResourceException e) {
            e.printStackTrace();
            showAlert("Error", "Language files not found in 'lang/' folder!");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        emailField.clear();
        passwordField.clear();
    }

    private void handleSuccessfulLogin() {
        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished(event -> {
            try {
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
        boolean loginSuccessful = validateLogin();

        if (loginSuccessful) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/App/homeDashboard.fxml"));
                Stage stage = (Stage) loginButton.getScene().getWindow();
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

    @FXML
    private void handleUserTypeMenuButton(ActionEvent event) {
        System.out.println("MenuButton clicked");
    }

    @FXML
    private void handleStudentSelection(ActionEvent event) {
        selectedUserType = "Student";
        userTypeMenuButton.setText(bundle != null ? bundle.getString("studentMenuItem") : "Student");
    }

    @FXML
    private void handleAdminSelection(ActionEvent event) {
        selectedUserType = "Administrator";
        userTypeMenuButton.setText(bundle != null ? bundle.getString("adminMenuItem") : "Administrator");
    }
}