package com.ooad.pixeledit.models;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private String id;
    private String username;
    private String fullname;
    private String password;
    private String email;
    private String address;

    public User() {
    }

    public User(String username, String fullname, String password, String email, String address) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}