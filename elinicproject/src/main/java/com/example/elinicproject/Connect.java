package com.example.elinicproject;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Connect {

    private static String URL = "jdbc:mysql://34.118.11.197/ECLN";
    private static String user = "root";
    private static String password = "projekteclinic2024uewrockpz";

    public static Connection conn() {
        try {
            return DriverManager.getConnection(URL, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd podczas uzyskiwania połączenia z bazą danych", e);
        }
    }


}