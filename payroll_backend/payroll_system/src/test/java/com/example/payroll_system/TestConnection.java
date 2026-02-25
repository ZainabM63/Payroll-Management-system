package com.example.payroll_system;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        try {
            String url = "jdbc:oracle:thin:@//localhost:1521/orcl";
            String username = " sys as sysdba";
            String password = "Uit54321";

            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful: " + connection);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
