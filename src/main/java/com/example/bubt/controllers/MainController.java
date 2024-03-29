package com.example.bubt.controllers;

import com.example.bubt.utils.ShowAlert;
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
    protected void onLogInButtonClick(ActionEvent event) throws Exception {
        try {
            SqlDB reqDB = new SqlDB();
            Connection connectDB = reqDB.getDatabaseLink();

            String email = fldEmail.getText().trim();
            String password = fldPassword.getText().trim();
            String role = userRole.getValue();

            String connectQuery = "SELECT Name, Email, Password, Role FROM usertable WHERE Email=?";

            PreparedStatement statement = connectDB.prepareStatement(connectQuery);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("Name");
                String retrievedEmail = resultSet.getString("Email");
                String retrievedPassword = resultSet.getString("Password");
                String retrievedRole = resultSet.getString("Role");

                if (retrievedEmail.equals(email) && retrievedPassword.equals(password)) {
                    switch (retrievedRole) {
                        case "Admin":
                            changeScreen(event, "/com/example/bubt/views/Admin-view.fxml", "BUBT-Admin");
                            break;
                        case "Student":
                            changeScreen(event, "/com/example/bubt/views/Student-view.fxml", "BUBT-Student");
                            break;
                        case "Teacher":
                            changeScreen(event, "/com/example/bubt/views/Teacher-view.fxml", "BUBT-Teacher");
                            break;
                        case "Guest":
                            // Handle guest scenario
                            break;
                        default:
                            WorningTxt.setText("Please Select Role");
                            break;
                    }
                    WorningTxt.setText("");
                } else {
                    WorningTxt.setText("Invalid credentials");
                }
            } else {
                WorningTxt.setText("Email does not Match!");
            }

            resultSet.close();
            statement.close();
            connectDB.close();
        } catch (SQLException e) {
            ShowAlert alert = new ShowAlert();
            alert.showAlert("Error", "MySQL JDBC Driver not found!" + e.getMessage());
            e.printStackTrace();

        }
    }

    @FXML
    protected void onSingUpButtonClick(ActionEvent event) throws Exception {
        changeScreen(event, "/com/example/bubt/views/singup-view.fxml", "BUBT-SingUp");
    }

    private void changeScreen(ActionEvent event, String viewPath, String title) {
        try {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

            changeSceen signUp = new changeSceen(viewPath, title);
            Stage newStage = new Stage();
            signUp.start(newStage);

            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
