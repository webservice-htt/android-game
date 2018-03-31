package com.example.baobang.gameduangua.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.data.ApiUtils;
import com.example.baobang.gameduangua.data.SOService;
import com.example.baobang.gameduangua.login.view.LoginActivity;
import com.example.baobang.gameduangua.model.User;
import com.example.baobang.gameduangua.model.UserRequest;
import com.example.baobang.gameduangua.model.UserResponse;
import com.example.baobang.gameduangua.utils.AppUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huuduc on 04/03/2018.
 */

public class SignupActivity extends AppCompatActivity{

    public static  final String BUNDLE_KEY_USERNAME = "username";
    public static final String BUNDLE_KEY_PASSWORD = "password";
    public static final String REGULAR_EMAIL = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";

    private EditText txtUsername, txtPassword, txtPasswordComfirm;
    private Button btnRegister;
    private ProgressBar progressBar;
    private SOService mSoService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        btnRegister = findViewById(R.id.btnRegister);
        txtUsername = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);
        txtPasswordComfirm = findViewById(R.id.passwordComfirm);
        progressBar = findViewById(R.id.progressBar);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String passwordConfirm = txtPasswordComfirm.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!email.matches(REGULAR_EMAIL)){
                    Toast.makeText(getApplicationContext(), "Format email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!password.equals(passwordConfirm)){
                    Toast.makeText(getApplicationContext(), "Password and password confirm do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                mSoService = ApiUtils.getSOService();

                mSoService.register(new UserRequest(email, password))
                        .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        UserResponse userResponse = response.body();
                        if (userResponse.getStatus() == 404){
                            Toast.makeText(SignupActivity.this, "Email exits", Toast.LENGTH_SHORT).show();

                        }else{
                           backToLoginActivity(email, password);
                        }
                        progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(SignupActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    private void backToLoginActivity(String email, String password) {
        Intent intent = new Intent(this, LoginActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_USERNAME, email);
        bundle.putString(BUNDLE_KEY_PASSWORD, password);

        intent.putExtras(bundle);

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
