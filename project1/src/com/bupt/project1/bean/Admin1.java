package com.bupt.project1.bean;

public class Admin1 {
    int aid;
    String username;
    String password;

    public Admin1() {
    }

    public Admin1(int aid, String username, String password) {
        this.aid = aid;
        this.username = username;
        this.password = password;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin1{" +
                "aid=" + aid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
