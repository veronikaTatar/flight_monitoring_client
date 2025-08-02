package main.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserMenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button UpdateAddressButton;

    @FXML
    private Button UpdateAgeButton;

    @FXML
    private Button UpdateLoginButton;

    @FXML
    private Button UpdatePasswordButton;

    @FXML
    private Button UpdateSexButton;

    @FXML
    private TextField UpdateloginButton;

    @FXML
    private Button buttonBack;

    @FXML
    private Label labelMessage;


    @FXML
    void Signup_Pressed(ActionEvent event) {

    }

    @FXML
    void initialize() {


    }


    @FXML
    public void Back_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/Login.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }
}