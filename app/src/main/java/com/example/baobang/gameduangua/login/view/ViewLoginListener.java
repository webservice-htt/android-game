package com.example.baobang.gameduangua.login.view;

import com.example.baobang.gameduangua.model.User;

/**
 * Created by baobang on 1/29/18.
 */

public interface ViewLoginListener {
    void onLoginSuccess(User user);
    void onLoginFailed();
}
