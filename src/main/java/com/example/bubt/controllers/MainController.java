package com.example.bubt.controllers;

import com.example.bubt.utils.SqlDB;
import com.example.bubt.utils.changeSceen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    private ComboBox<String> userRole;

    @FXML
    private TextField fldEmail;

    @FXML
    private Label WorningTxt;

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
    public void initialize() {
        ObservableList<String> values = FXCollections.observableArrayList(
                "Admin",
                "Student",
                "Teacher",
                "Guest"
        );
        userRole.setItems(values);
    }

    @FXML
    protected  void  onLogInButtonClick(ActionEvent event) throws Exception
    {
        SqlDB reqDB = new SqlDB();
        Connection connectDB = reqDB.getDatabaseLink();

        String email = fldEmail.getText().trim();
        String password = fldPassword.getText().trim();
        String role = userRole.getValue();

        String connectQuery = "SELECT Name, Email, Password,Role FROM usertable WHERE Email='"+email+"'";


        try {
            PreparedStatement statement = connectDB.prepareStatement(connectQuery);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("Name");
                String retrievedEmail = resultSet.getString("Email");
                String retrievedPassword = resultSet.getString("Password");
                String retrievedRole = resultSet.getString("Role");

                if (retrievedEmail.equals(email) && retrievedPassword.equals(password))
                {
                    if(retrievedRole.equals("Admin"))
                    {
                        // admin sceen
                        Node source = (Node) event.getSource();
                        Stage stage = (Stage) source.getScene().getWindow();
                        stage.close();

                        changeSceen SingUp = new changeSceen("/com/example/bubt/views/Admin-view.fxml","BUBT-Admin");
                        Stage newStage = new Stage();
                        SingUp.start(newStage);

                        newStage.show();
                    }
                    else if (retrievedRole.equals("Student")) {
                        // Student sceen
                        Node source = (Node) event.getSource();
                        Stage stage = (Stage) source.getScene().getWindow();
                        stage.close();

                        changeSceen SingUp = new changeSceen("/com/example/bubt/views/Student-view.fxml","BUBT-Student");
                        Stage newStage = new Stage();
                        SingUp.start(newStage);

                        newStage.show();
                    }
                    else if (retrievedRole.equals("Teacher")) {
                        // Teacher Sceen
                        Node source = (Node) event.getSource();
                        Stage stage = (Stage) source.getScene().getWindow();
                        stage.close();

                        changeSceen SingUp = new changeSceen("/com/example/bubt/views/Teacher-view.fxml","BUBT-Teacher");
                        Stage newStage = new Stage();
                        SingUp.start(newStage);

                        newStage.show();
                    }
                    else if (retrievedRole.equals("Guest")) {
                        // guest Sceen
                    }
                    else {
                        WorningTxt.setText("Please Select Role");
                    }

                    WorningTxt.setText("");
                }
                else
                {
                    WorningTxt.setText("Invalid credentials");
                }
            } else {
                WorningTxt.setText("Email dose not Match!");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void onSingUpButtonClick(ActionEvent event) throws Exception {
        // Load the new FXML file

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        changeSceen SingUp = new changeSceen("/com/example/bubt/views/singup-view.fxml","BUBT-SingUp");
        Stage newStage = new Stage();
        SingUp.start(newStage);

        newStage.show();
    }

}

        /*
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/bubt/views/singup-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        Scene newScene = new Scene(root, 900, 500);
        newStage.setScene(newScene);
        newStage.setTitle("BUBT-SingUp");
        */
        //statement = connectDB.prepareStatement(connectQuery);
        // statement.setString(1, email);
        // resultSet = statement.executeQuery();