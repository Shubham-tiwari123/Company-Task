package com.project.demo.entity;

public class RegisterUser {

    private String userEmail;
    private String password;
    private String name;

    public RegisterUser() {

    }

    public RegisterUser(String userEmail, String password, String name) {
        this.userEmail = userEmail;
        this.password = password;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
