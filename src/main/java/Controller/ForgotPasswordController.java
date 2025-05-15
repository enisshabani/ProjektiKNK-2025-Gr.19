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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
public class ForgotPasswordController {

    @FXML
    private AnchorPane fpApplication;

    @FXML
    private TextField emailField;

    @FXML
    private TextField kodiKonfirmimit;

    @FXML
    private Hyperlink helpLink;

    @FXML
    private Hyperlink signupLink;

    @FXML
    private Button continueButton;

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleContinueButtonAction() {
        String email = emailField.getText();
        String confirmationCode = kodiKonfirmimit.getText();

        System.out.println("Email: " + email);
        System.out.println("Confirmation Code: " + confirmationCode);

    }

}
