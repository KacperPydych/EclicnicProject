package com.example.elinicproject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Login {




    public static boolean validateUserCredentials(String login, String password) {


        try {
            String query = "SELECT Haslo FROM login WHERE Login = ?";
            try (PreparedStatement statement = Connect.conn().prepareStatement(query)) {
                statement.setString(1, login);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String storedPassword = resultSet.getString("Haslo");
                        return checkPassword(password, storedPassword);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error validating user credentials", e);
        }

        return false;
    }
    private static boolean checkPassword(String inputPassword, String storedPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedInputPassword = md.digest(inputPassword.getBytes());


            StringBuilder sb = new StringBuilder();
            for (byte b : hashedInputPassword) {
                sb.append(String.format("%02x", b));
            }

            // Porównaj hasła
            return sb.toString().equals(storedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Błąd podczas weryfikacji hasła.", e);
        }
    }

}
