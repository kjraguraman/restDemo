package org.rest;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private DBConnect(){}
    public static Connection getConnection(){
        System.out.println("Getting connection object");
         Connection con=null;
            try {
                Class.forName("org.postgresql.Driver");
                String url="jdbc:postgresql://localhost:5432/restpractice";
                String username="postgres";
                String password="postgres";
                con= DriverManager.getConnection(url,username,password);
                return con;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection Failed");
            }
            return con;
     }

}
