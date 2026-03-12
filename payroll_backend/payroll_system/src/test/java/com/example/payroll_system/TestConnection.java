package com.example.payroll_system;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        // PostgreSQL connection parameters
        String url = "jdbc:postgresql://localhost:5432/postgres"; // replace 'postgres' with your DB name
        String username = "postgres";
        String password = "zainab"; // your PostgreSQL password

        try {
            // Load PostgreSQL driver (optional for modern JDBC)
            Class.forName("org.postgresql.Driver");

            // Establish connection
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful: " + connection);

            // Close connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}