package com.udacity.jwdnd.course1.cloudstorage.model;

import java.util.Objects;

public class User {
    private Integer userId;
    private String username;
    private String salt;
    private String pass;
    private String firstname;
    private String lastname;


    public User(Integer userId, String username, String salt, String pass, String firstname, String lastname) {
        this.userId = userId;
        this.username = username;
        this.salt = salt;
        this.pass = pass;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(salt, user.salt) && Objects.equals(pass, user.pass) && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, salt, pass, firstname, lastname);
    }
}
