package com.example.baobang.gameduangua.login.model;


import android.util.Log;

import com.example.baobang.gameduangua.data.ApiUtils;
import com.example.baobang.gameduangua.data.SOService;
import com.example.baobang.gameduangua.model.UserRequest;
import com.example.baobang.gameduangua.model.User;
import com.example.baobang.gameduangua.model.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huuduc on 04/02/2018.
 */

public class ModelLogin {
    private ModelResponseToPresenterListener callback;
    private SOService soService;

    public ModelLogin(ModelResponseToPresenterListener callback) {
        this.callback = callback;
    }

    public void handleLogin(String email, String password){
        final UserRequest loginRequest = new UserRequest(email, password);

        Log.d("Login+handle", " onClick: " + email + ", " + password);
        soService = ApiUtils.getSOService();
        soService.login(loginRequest).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if (userResponse.getStatus() == 404){
                    callback.onLoginFailed();
                }else{
                    User user = userResponse.getUser();
                    callback.onLoginSuccess(user);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                callback.onLoginFailed();
            }
        });
    }
}
