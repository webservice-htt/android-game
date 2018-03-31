package com.example.baobang.gameduangua.model;

/**
 * Created by baobang on 1/29/18.
 */

public class UserRequest {

    private String email;
    private String password;

    public UserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "{\"email\" : \""+ email +"\", \"password\" : \""+ password +"\"}";
    }
}
