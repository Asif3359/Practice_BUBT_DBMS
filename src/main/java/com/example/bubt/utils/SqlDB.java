package com.example.bubt.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDB {
   public Connection databaseLink ;
   public Connection getDatabaseLink () {

        String url ="jdbc:mysql://localhost/university" ;
        String user="root";
        String password="";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaseLink = (Connection) DriverManager.getConnection(url,user,password);

            System.out.println("connect successfully");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return databaseLink;
   }
}
