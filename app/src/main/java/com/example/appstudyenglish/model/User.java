package com.example.appstudyenglish.model;

public class User {
    private String maUser;
    private String name;
    private String email;
    private int permission;

    public User(){

    }

    public User(String maUser, String name, String email, int permission) {
        this.maUser = maUser;
        this.name = name;
        this.email = email;
        this.permission = permission;
    }

    public String getMaUser() {
        return maUser;
    }

    public void setMaUser(String maUser) {
        this.maUser = maUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
