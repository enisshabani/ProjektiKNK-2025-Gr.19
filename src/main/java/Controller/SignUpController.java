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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.MissingResourceException;

import java.io.IOException;

public class SignUpController {

    @FXML private TextField nameField;
    @FXML private TextField lastnameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button signupButton;
    @FXML private Hyperlink loginLink;
    @FXML private Hyperlink helpLink;
    @FXML private Hyperlink aboutLink;
    @FXML private Button languageButton;
    @FXML private Label nameLabel;
    @FXML private Label lastnameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label universityLabel;
    @FXML private Label universityYearLabel;
    @FXML private Label gradeLabel;
    @FXML private Label genderLabel;
    @FXML private MenuButton selectMenuButton;
    @FXML private MenuItem firstMenuItem;
    @FXML private MenuItem secondMenuItem;
    @FXML private MenuItem thirdMenuItem;
    @FXML private RadioButton maleRadioButton;
    @FXML private RadioButton femaleRadioButton;
    private ResourceBundle bundle;
    private boolean isAlbanian = false;

    // EXISTING INITIALIZE (ADDED ONE LINE)
    @FXML
    private void initialize() {
        loadLanguage(false); // ONLY ADDED THIS LINE
    }

    // NEW METHOD (ADDED)
    @FXML
    private void handleLanguageSwitch(ActionEvent event) {
        isAlbanian = !isAlbanian;
        loadLanguage(isAlbanian);
    }

    // NEW METHOD (ADDED)
    private void loadLanguage(boolean isAlbanian) {
        try {
            Locale locale = isAlbanian ? new Locale("al") : Locale.ENGLISH;
            bundle = ResourceBundle.getBundle("language/strings", locale);

            nameLabel.setText(bundle.getString("nameLabel"));
            lastnameLabel.setText(bundle.getString("lastnameLabel"));
            passwordLabel.setText(bundle.getString("passwordLabel"));
            universityLabel.setText(bundle.getString("universityLabel"));
            universityYearLabel.setText(bundle.getString("universityYearLabel"));
            gradeLabel.setText(bundle.getString("gradeLabel"));
            genderLabel.setText(bundle.getString("genderLabel"));
            selectMenuButton.setText(bundle.getString("selectMenuButton"));
            firstMenuItem.setText(bundle.getString("firstMenuItem"));
            secondMenuItem.setText(bundle.getString("secondMenuItem"));
            thirdMenuItem.setText(bundle.getString("thirdMenuItem"));
            maleRadioButton.setText(bundle.getString("maleRadioButton"));
            femaleRadioButton.setText(bundle.getString("femaleRadioButton"));
            signupButton.setText(bundle.getString("signupButton"));
            loginLink.setText(bundle.getString("loginLink"));
            helpLink.setText(bundle.getString("helpLink"));
            aboutLink.setText(bundle.getString("aboutLink"));
            languageButton.setText(isAlbanian ? "EN | AL" : "AL | EN");
        } catch (MissingResourceException e) {
            e.printStackTrace();
        }
    }

    // EXISTING METHODS BELOW (ALL UNCHANGED)
    @FXML
    private void handleSignUp(ActionEvent event) {
        String name = nameField.getText();
        String lastname = lastnameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

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
            Parent root = FXMLLoader.load(getClass().getResource("/App/loginApplication.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}