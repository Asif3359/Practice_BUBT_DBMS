package com.example.bubt.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDB {

    Connection cnt ;
    String url ="jdbc:mysql://localhost/mysql" ;
    String user="root";
    String password="";

    public void connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnt = (Connection) DriverManager.getConnection(url,user,password);

            System.out.println("connect successfully");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
