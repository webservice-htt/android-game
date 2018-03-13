package com.example.baobang.gameduangua.login.presenter;

import android.util.Log;

import com.example.baobang.gameduangua.model.User;
import com.example.baobang.gameduangua.login.model.ModelLogin;
import com.example.baobang.gameduangua.login.model.ModelResponseToPresenterListener;
import com.example.baobang.gameduangua.login.view.ViewLoginListener;

/**
 * Created by baobang on 1/29/18.
 */

public class LoginPresenter implements ModelResponseToPresenterListener {

    private ModelLogin modelLogin;
    private ViewLoginListener callback;

    public LoginPresenter(ViewLoginListener callback) {
        this.callback = callback;
    }

    public void receiveHandleLogin(String email, String password){
        modelLogin = new ModelLogin(this);


        modelLogin.handleLogin(email, password);
    }

    @Override
    public void onLoginSuccess(User user) {
        callback.onLoginSuccess(user);
    }

    @Override
    public void onLoginFailed() {
        callback.onLoginFailed();
    }
}
