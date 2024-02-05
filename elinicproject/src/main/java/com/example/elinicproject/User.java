package com.example.elinicproject;
import java.sql.Time;
import java.time.LocalDate;

public class User {

    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String pesel;
    private LocalDate dateOfBirth;
    private String number;

    private LocalDate dataWizyty;
    private String powod;


    public  String getName(){
        return name;
    }
    public LocalDate getDataWizyty(){return dataWizyty;}
    public String getPowod(){return powod;}
    public  String getSurname(){
        return surname;
    } public  String getEmail(){
        return email;
    }
    public  String getPesel(){
        return pesel;
    } public  LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
    public  String getNumber(){
        return number;
    }
    public  String getLogin(){
        return login;
    }
    public  String getPassword(){
        return password;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setEmail(String email) {this.email = email;}
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setPesel(String pesel) {this.pesel = pesel;}
    public void setnumber(String number) {this.number = number;}
    public void setPowod(String powod){this.powod=powod;}
    public void setDataWizyty(LocalDate dataWizyty){this.dataWizyty=dataWizyty;}


}
