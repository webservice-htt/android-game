package com.example.baobang.gameduangua.login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baobang.gameduangua.Constant;
import com.example.baobang.gameduangua.all_course.ListCourseActivity;
import com.example.baobang.gameduangua.all_course.detail.CourseDetailActivity;
import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.login.presenter.LoginPresenter;
import com.example.baobang.gameduangua.model.User;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements ViewLoginListener, View.OnClickListener {

    private LoginPresenter loginPresenter;
    private EditText txtUsername, txtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addControls();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = getSharedPreferences(Constant.KEY_PREFERENCES, Context.MODE_PRIVATE);
        String userString = preferences.getString(Constant.USER, "");


        if (!TextUtils.isEmpty(userString)){

                Intent intent = new Intent(this, ListCourseActivity.class);
                intent.putExtra(Constant.USER, userString);

                startActivity(intent);
                finish();
        }
    }

    private void addControls() {
        loginPresenter = new LoginPresenter(this);
        txtUsername = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onLoginSuccess(User user) {

        SharedPreferences preferences = getSharedPreferences(Constant.KEY_PREFERENCES, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(user);

        Toast.makeText(LoginActivity.this, "Login Thanh Cong : " + user.getName(), Toast.LENGTH_SHORT).show();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.USER, json);
        editor.apply();

        Intent mainInter = new Intent(this, ListCourseActivity.class);
        mainInter.putExtra(Constant.USER, json);
        startActivity(mainInter);
        finish();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(LoginActivity.this, "Email hoặc password sai. Vui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                String email = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                    loginPresenter.receiveHandleLogin(email, password);
                }else{
                    Toast.makeText(LoginActivity.this, "Vui lòng điền đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
