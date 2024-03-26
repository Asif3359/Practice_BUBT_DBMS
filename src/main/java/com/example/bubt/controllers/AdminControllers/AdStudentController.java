package com.example.bubt.controllers.AdminControllers;

import com.example.bubt.utils.SqlDB;
import com.example.bubt.utils.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    public void OnSubmitStudentClicked() {
        String name = StFdName.getText();
        String email = StFdEmail.getText();
        String subject = StFdSubject.getText();
        String intake = StFdIntake.getText();
        String phone = StFdPhone.getText();
        String section = StFdSection.getText();
        String password = StFdPassword.getText();
        String address = StFdAddress.getText();
        String city = StFdCity.getText();

        if (name.isEmpty() || email.isEmpty() || subject.isEmpty() || intake.isEmpty() || phone.isEmpty() || section.isEmpty() || password.isEmpty() || address.isEmpty()) {
            showAlert("Error", "Text field is empty");
        } else {
            try {
                SqlDB reqDB = new SqlDB();

                // Insert data into 'usertable'
                String role = "Student";
                String sqlUser = "INSERT INTO usertable (Name, Email, Password, Role) VALUES (?, ?, ?, ?)";
                boolean userInserted = reqDB.ExecuteUpdate(sqlUser, new Object[]{name, email, password, role});

                if (userInserted) {

                    String getUserIdQuery = "SELECT id FROM usertable WHERE Email=? AND Password=?";
                    Object[] params = {email, password};
                    ResultSet getUserId = reqDB.ExecuteQuery(getUserIdQuery, params);

                    int userID = 0;
                    if (getUserId.next()) {
                        userID = getUserId.getInt("id");
                    }

                    // Insert data into 'studenttable'
                    String sqlStudent = "INSERT INTO studenttable (user_ID, Name, Email, Intake, Section, Phone, Address, Subject, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    boolean studentInserted = reqDB.ExecuteUpdate(sqlStudent, new Object[]{userID, name, email, intake, section, phone, address, subject, password});

                    if (studentInserted) {
                        clearTextFields();
                        populateTableView();
                        showAlert("Success", "A new record has been inserted successfully.");
                    } else {
                        showAlert("Error", "Some problem occurred while inserting a new record.");
                    }
                } else {
                    showAlert("Error", "User data could not be inserted.");
                }
            } catch (SQLException e) {
                showAlert("Error", "Error inserting record: " + e.getMessage());
            }
        }
    }

    @FXML
    public void initialize() {
        populateTableView();
    }

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
            SqlDB reqDB = new SqlDB();
            String query = "SELECT * FROM studenttable";
            ResultSet resultSet = reqDB.prepareStatement(query).executeQuery();

            ObservableList<Student> students = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Student student = new Student(resultSet.getInt("user_ID"), resultSet.getString("Name"), resultSet.getString("Email"), resultSet.getString("Intake"), resultSet.getString("Section"), resultSet.getString("Phone"), resultSet.getString("Address"), resultSet.getString("Subject"), resultSet.getString("Password"));
                students.add(student);
            }

            stViewTable.setItems(students);

            resultSet.close();
        } catch (SQLException e) {
            showAlert("Error", "Failed to fetch data from the database.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearTextFields() {
        StFdName.clear();
        StFdEmail.clear();
        StFdSubject.clear();
        StFdIntake.clear();
        StFdPhone.clear();
        StFdSection.clear();
        StFdPassword.clear();
        StFdAddress.clear();
        StFdCity.clear();
    }
}
