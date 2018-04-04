package com.example.baobang.gameduangua.model;

/**
 * Created by baobang on 4/3/18.
 */

public class UserUpdateReQuest {
    private String name;
    private String email;
    private String birthday;
    private boolean gender;

    public UserUpdateReQuest(String name, String email, String birthday, boolean gender) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
