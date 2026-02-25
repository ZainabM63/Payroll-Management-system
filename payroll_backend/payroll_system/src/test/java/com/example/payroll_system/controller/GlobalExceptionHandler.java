package com.example.payroll_system.controller;

    import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQLException(SQLException ex) {
        String errorMessage = ex.getMessage();
        // Handle specific trigger error codes
        if (errorMessage.contains("ORA-20001")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Employee must be at least 18 years old.");
        } else if (errorMessage.contains("ORA-20002")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Join date must be after the birthdate.");
        } else if (errorMessage.contains("ORA-00942")) {
            // This error code usually means that a referenced table or view doesn't exist
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("One or more referenced tables or views do not exist.");
        } else if (errorMessage.contains("ORA-04098")) {
            // Trigger related error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)                                 .body("Error occurred while processing trigger: Trigger is invalid or failed re-validation.");
        }
        // Handle errors from the archive deleted employee data trigger
        if (errorMessage.contains("ORA-00001")) {
            // ORA-00001: Unique constraint violated (typically on deleted records, if trying to insert duplicates)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Duplicate record found while archiving deleted employee data.");
        }
        // Generic database error
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Database error: " + errorMessage);
    }
}
