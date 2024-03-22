package com.example.bubt.controllers;

import com.example.bubt.Main;
import com.example.bubt.utils.changeSceen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SingUpController  {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField fldEmail;

    @FXML
    private TextField fldPassword;

    @FXML
    private Button btnLogIn;

    @FXML
    private Button btnSingUp;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected  void  onSingUpButtonClick()
    {

        fldEmail.setText("asifahammedNishat@gmail.com");
    }
    @FXML
    protected void onLogInButtonClick (ActionEvent event) throws Exception {
        // Load the new FXML file

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        // Main main = new Main();
        // Stage newStage = new Stage();
        // main.start(newStage);

        changeSceen login = new changeSceen("/com/example/bubt/Main-view.fxml","BUBT-Login");
        Stage newStage = new Stage();
        login.start(newStage);

    }
}
