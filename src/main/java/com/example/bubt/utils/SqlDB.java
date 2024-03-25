package com.example.bubt.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlDB {
   public Connection databaseLink ;
   public String sqlStatement;
   public PreparedStatement statement;
   public Connection getDatabaseLink () {

        String url ="jdbc:mysql://localhost/university" ;
        String user="root";
        String password="";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaseLink = (Connection) DriverManager.getConnection(url,user,password);

            //System.out.println("connect successfully");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return databaseLink;
   }

   public PreparedStatement Statement(String sqlStatement )
   {
       this.sqlStatement = sqlStatement;
       Connection connectDB = getDatabaseLink();
       try {
            statement = connectDB.prepareStatement(sqlStatement);
            return statement ;

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

   }
}
