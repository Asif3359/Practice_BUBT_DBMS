package com.example.bubt.controllers.AdminControllers;

import com.example.bubt.utils.SqlDB;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdStudentController {
    @FXML
    private Button btnSubmitStudent;

    @FXML
    private TextField StFdName;
    @FXML
    private TextField StFdEmail;
    @FXML
    private TextField StFdSubject;
    @FXML
    private TextField StFdIntake;
    @FXML
    private TextField StFdPhone;
    @FXML
    private TextField StFdSection;
    @FXML
    private TextField StFdPassword;
    @FXML
    private TextField StFdAddress;
    @FXML
    private TextField StFdCity;
    @FXML
    public void  OnSubmitStudentClicked(){
        String name = StFdName.getText();
        String email = StFdEmail.getText();
        String subject = StFdSubject.getText();
        String intake = StFdIntake.getText();
        String phone = StFdPhone.getText();
        String section = StFdSection.getText();
        String password = StFdPassword.getText();
        String address = StFdAddress.getText();
        String city = StFdCity.getText();

        try {
            SqlDB reqDB = new SqlDB();
            Connection connectDB = reqDB.getDatabaseLink();
            String Role = "Student";

            String sqlUser = "INSERT INTO usertable (Name, Email, Password, Role) VALUES (?, ?, ?, ?)";

            PreparedStatement userStatement = connectDB.prepareStatement(sqlUser);
            userStatement.setString(1, name);
            userStatement.setString(2, email);
            userStatement.setString(3, password);
            userStatement.setString(4, Role);

            int userInserted = userStatement.executeUpdate();

            if (userInserted > 0)
            {
                String getUserIdQuery = "SELECT id FROM usertable WHERE Email='"+email+"' AND Password='"+password+"'";

                PreparedStatement userIdStatement = connectDB.prepareStatement(getUserIdQuery);
                ResultSet getUserId = userIdStatement.executeQuery();

                if(getUserId.next())
                {
                    int userID = getUserId.getInt("id");

                    String sql = "INSERT INTO studenttable (user_ID, Name, Email, Intake, Section, Phone, Address, Subject, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = connectDB.prepareStatement(sql);
                    statement.setInt(1, userID);
                    statement.setString(2, name);
                    statement.setString(3, email);
                    statement.setString(4, intake);
                    statement.setString(5, section);
                    statement.setString(6, phone);
                    statement.setString(7, address);
                    statement.setString(8, subject);
                    statement.setString(9, password);

                    int rowsInserted = statement.executeUpdate();

                    if (rowsInserted > 0) {
                         StFdName.setText("");
                         StFdEmail.setText("");
                         StFdSubject.setText("");
                         StFdIntake.setText("");
                         StFdPhone.setText("");
                         StFdSection.setText("");
                         StFdPassword.setText("");
                         StFdAddress.setText("");
                         StFdCity.setText("");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("A new record has been inserted successfully.");
                        alert.showAndWait();
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Some problem to insert New Record");
                        alert.showAndWait();
                    }
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("User data don't inserted");
                alert.showAndWait();
            }



        } catch (SQLException e) {
            System.out.println("Error inserting record: " + e.getMessage());
        }
    }
}
