package com.example.bubt.utils;

import javafx.scene.control.Alert;

import java.sql.*;

public class SqlDB {
    public Connection getDatabaseLink() throws SQLException {
        String url = "jdbc:mysql://localhost/university";
        String user = "root";
        String password = "";

        try {
            // Load the MySQL JDBC driver class
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            showAlert("Error", "MySQL JDBC Driver not found!");
            throw new RuntimeException(e);
        }

    }

    public PreparedStatement prepareStatement(String sqlStatement) {
        try {
            Connection connectDB = getDatabaseLink();
            return connectDB.prepareStatement(sqlStatement);
        } catch (SQLException e) {
            showAlert("Error", "Database is not connected!");
            throw new RuntimeException(e);
        }
    }

    public boolean ExecuteUpdate(String sql, Object[] params) {
        try {
            int rowsAffected;
            try (Connection connection = getDatabaseLink();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                for (int i = 0; i < params.length; i++) {
                    switch (params[i]) {
                        case String s -> statement.setString(i + 1, s);
                        case Integer integer -> statement.setInt(i + 1, integer);
                        case Double v -> statement.setDouble(i + 1, v);
                        case null, default ->
                                throw new IllegalArgumentException("Unsupported parameter type: " + params[i].getClass());
                    }
                }
                rowsAffected = statement.executeUpdate();
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet ExecuteQuery(String sql, Object[] params) {
        try {
            Connection connection = getDatabaseLink();
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                switch (params[i]) {
                    case String s -> statement.setString(i + 1, s);
                    case Integer integer -> statement.setInt(i + 1, integer);
                    case Double v -> statement.setDouble(i + 1, v);
                    case null, default ->
                            throw new IllegalArgumentException("Unsupported parameter type: " + params[i].getClass());
                }
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // or throw an exception
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(null);
        alert.showAndWait();
    }
}
