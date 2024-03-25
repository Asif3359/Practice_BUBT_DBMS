package com.example.bubt.controllers.AdminControllers;

import com.example.bubt.utils.SqlDB;
import com.example.bubt.utils.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<Student> stViewTable;
    @FXML
    private TableColumn<Student, Integer> TablestId;
    @FXML
    private TableColumn<Student, String> TableStName;
    @FXML
    private TableColumn<Student, String> TableStEmail;
    @FXML
    private TableColumn<Student, String> TableStIntake;
    @FXML
    private TableColumn<Student, String> TableStSection;
    @FXML
    private TableColumn<Student, String> TableStPhone;
    @FXML
    private TableColumn<Student, String> TableStAddress;
    @FXML
    private TableColumn<Student, String> TableStSubject;
    @FXML
    private TableColumn<Student, String> TableStPassword;
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

        if(name.isEmpty() || email.isEmpty() || subject.isEmpty() || intake.isEmpty() || phone.isEmpty() || section.isEmpty() || password.isEmpty()|| address.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Text field is empty");
            alert.setContentText(null);
            alert.showAndWait();
        }
        else {
            try {
                SqlDB reqDB = new SqlDB();
                Connection connectDB = reqDB.getDatabaseLink();

                String Role = "Student";
                String sqlUser = "INSERT INTO usertable (Name, Email, Password, Role) VALUES (?, ?, ?, ?)";
                PreparedStatement userStatement = reqDB.Statement(sqlUser);
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
                            alert.setHeaderText("A new record has been inserted successfully.");
                            alert.setContentText(null);
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
    @FXML
    public void initialize() {

        populateTableView();
    }

    // Method to fetch data from the database and populate the TableView
    private void populateTableView() {
        TablestId.setCellValueFactory(new PropertyValueFactory<>("userID"));
        TableStName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableStEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableStIntake.setCellValueFactory(new PropertyValueFactory<>("intake"));
        TableStSection.setCellValueFactory(new PropertyValueFactory<>("section"));
        TableStPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        TableStAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        TableStSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        TableStPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        try {
            // Create a connection to your database
            SqlDB reqDB = new SqlDB();
            Connection connection = reqDB.getDatabaseLink(); // Replace YourDatabaseUtil.getConnection() with your actual method to get a database connection

            // SQL query to fetch data from the studenttable
            String query = "SELECT * FROM studenttable";

            // Create a PreparedStatement object
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Execute the query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create an ObservableList to store the student data
            ObservableList<Student> students = FXCollections.observableArrayList();

            // Loop through the result set and add student objects to the ObservableList
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt("user_ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getString("Intake"),
                        resultSet.getString("Section"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Address"),
                        resultSet.getString("Subject"),
                        resultSet.getString("Password")
                );
                students.add(student);
            }

            // Set the data to the TableView
            stViewTable.setItems(students);

            // Close the result set, statement, and connection
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to fetch data");
            alert.setContentText("An error occurred while fetching data from the database.");
            alert.showAndWait();
        }
    }
}


