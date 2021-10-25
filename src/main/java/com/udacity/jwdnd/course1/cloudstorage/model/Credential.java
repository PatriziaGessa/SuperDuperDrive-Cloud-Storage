package com.udacity.jwdnd.course1.cloudstorage.model;

import java.util.Objects;

public class Credential {
    private int credentialId;
    private String url;
    private String username;
    private String notCypherPassword;
    private String key;
    private String password;
    private int userId;

    public Credential() {

    }

    public Credential(int credentialId, String url, String username, String key, String password, int userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }

    public int getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(int credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNotCypherPassword() {
        return notCypherPassword;
    }

    public void setNotCypherPassword(String notCypherPassword) {
        this.notCypherPassword = notCypherPassword;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credential that = (Credential) o;
        return credentialId == that.credentialId && userId == that.userId && Objects.equals(url, that.url) && Objects.equals(username, that.username) && Objects.equals(notCypherPassword, that.notCypherPassword) && Objects.equals(key, that.key) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(credentialId, url, username, notCypherPassword, key, password, userId);
    }
}
