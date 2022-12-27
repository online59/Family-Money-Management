package com.example.famtrack.api;

import java.util.List;

public class User {
    private String uid;
    private String email;
    private List<Wallet> groups;
    private String name;

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Wallet> getGroups() {
        return groups;
    }

    public void setGroups(List<Wallet> groups) {
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
