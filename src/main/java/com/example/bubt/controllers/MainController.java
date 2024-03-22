package com.example.bubt.controllers;

import com.example.bubt.utils.SqlDB;
import com.example.bubt.utils.changeSceen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
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
    protected  void  onLogInButtonClick()
    {
        SqlDB req = new SqlDB();
        req.connect();

        fldEmail.setText("asifahammedNishat@gmail.com");
    }
    @FXML
    protected void onSingUpButtonClick(ActionEvent event) throws Exception {
        // Load the new FXML file

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        /*
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/bubt/views/singup-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        Scene newScene = new Scene(root, 900, 500);
        newStage.setScene(newScene);
        newStage.setTitle("BUBT-SingUp");
        */

        changeSceen SingUp = new changeSceen("/com/example/bubt/views/singup-view.fxml","BUBT-SingUp");
        Stage newStage = new Stage();
        SingUp.start(newStage);

        newStage.show();
    }
}