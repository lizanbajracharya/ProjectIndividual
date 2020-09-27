package com.example.projectindividual.model;

public class User {
    private String Id;
    private String mobileNumber;
    private String password;
    private String Email;
    private String username;

    public User(String mobileNumber, String password, String email, String username) {
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.Email = email;
        this.username = username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
