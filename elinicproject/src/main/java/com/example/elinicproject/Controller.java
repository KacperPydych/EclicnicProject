package com.example.elinicproject;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.sql.Statement;
@RestController
@RequestMapping("/api")
public class Controller {

    int currentUser = 0;

    @PostMapping("/postUser" )
    public void postUser (@RequestBody User user) {
        SignUp.singUP(user.getName(), user.getSurname(),user.getDateOfBirth(),user.getNumber(),user.getEmail(), user.getPesel(), user.getLogin(),user.getPassword());
    }


    @GetMapping("/pokazDane")
    public String pokazDane() {
        String sql = "SELECT DataWizyty, OpisWizyty FROM Wizyty WHERE PacjentID ='" + currentUser + "'";

        StringBuilder result = new StringBuilder();

        try (Connection con = Connect.conn();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    String dataWizyty = resultSet.getString("DataWizyty");
                    String opisWizyty = resultSet.getString("OpisWizyty");

                    result.append("Data Wizyty: ").append(dataWizyty).append(", Opis Wizyty: ").append(opisWizyty).append("\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();


    }


    @PostMapping("/postVisit" )
    public void postVisit (@RequestBody User user){

        String query = "INSERT INTO Wizyty ( PacjentID, DataWizyty, OpisWizyty) VALUES (?,?,?)";


        try (Connection con = Connect.conn();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, currentUser);
            java.sql.Date sqlDate = java.sql.Date.valueOf(user.getDataWizyty());
            pstmt.setDate(2, sqlDate);
            pstmt.setString(3, user.getPowod());




            int affectedRows = pstmt.executeUpdate();
            System.out.println(affectedRows + " rows affected.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error accessing database", e);
        }
    }
    @PostMapping("/checkUser")
    public Boolean checkUser(@RequestBody User user) throws SQLException {


        if(Login.validateUserCredentials(user.getLogin(),user.getPassword()) == true) {

            String query = "SELECT PacjentID from login WHERE Login = ?";
            try (Connection con = Connect.conn();
                 PreparedStatement pstmt = con.prepareStatement(query)) {

                pstmt.setString(1, user.getLogin());

                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {

                        currentUser = resultSet.getInt("PacjentID");


                    } else {
                        System.out.println("Nie znaleziono pasującego wiersza.");
                    }
                }
            }


        }

        return Login.validateUserCredentials(user.getLogin(),user.getPassword());

    }
    }



