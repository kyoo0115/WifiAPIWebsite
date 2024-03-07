package org.example.util;

import lombok.Data;

import java.sql.*;
import java.util.List;

import static org.example.util.Constants.*;

@Data
public class DatabaseConnection {

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}