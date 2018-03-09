package com.example.baobang.gameduangua.login.model;

import com.example.baobang.gameduangua.model.User;

/**
 * Created by huuduc on 04/02/2018.
 */

public interface ModelResponseToPresenterListener {
    void onLoginSuccess(User user);
    void onLoginFailed();
}
