package com.example.baobang.gameduangua.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.baobang.gameduangua.Constant;
import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.data.ApiUtils;
import com.example.baobang.gameduangua.data.SOService;
import com.example.baobang.gameduangua.model.User;
import com.example.baobang.gameduangua.model.UserResponse;
import com.example.baobang.gameduangua.model.UserUpdateReQuest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    private TextInputEditText txtFullName, txtDateOfBirth, txtEmail;
    private RadioButton rbMale, rbFemale;
    private Button btnUpdate, btnCancel;
    private SOService mSoService;

    private User user = null;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mSoService = ApiUtils.getSOService();
        getUserFromSharedPreferences();
        addControls();
        addEvents();
    }

    private void getUserFromSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences(Constant.KEY_PREFERENCES, Context.MODE_PRIVATE);
        String userString = preferences.getString(Constant.USER, "");
        if(userString.isEmpty()){
            return;
        }
        try {
            user = new User();
            Log.e("USER", userString);
            JSONObject jsonObject = new JSONObject(userString);
            user.setName(jsonObject.getString("name"));
            user.setEmail(jsonObject.getString("email"));
            user.setBirthday(jsonObject.getString("birthday"));
            user.setGender(jsonObject.optBoolean("gender"));
            user.setId(jsonObject.getString("_id"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addEvents() {
       btnCancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });
       btnUpdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               doClickUpdateButton();
           }
       });
    }

    private void doClickUpdateButton() {
        String fullName = txtFullName.getText().toString().trim();
        String dateOfBirth = txtDateOfBirth.getText().toString().trim();
        boolean gender = !rbMale.isChecked();
        String email = txtEmail.getText().toString().trim();

        if(fullName.isEmpty()){
            Toast.makeText(this, "Vui lòng nhập vào họ và tên", Toast.LENGTH_SHORT).show();
            return;
        }
        if(dateOfBirth.isEmpty()){
            Toast.makeText(this, "Vui lòng nhập vào ngày sinh", Toast.LENGTH_SHORT).show();
            return;
        }

        UserUpdateReQuest updateReQuest = new UserUpdateReQuest(fullName, email, dateOfBirth, gender);

       if(user != null){
           mSoService.updateUser(user.getId(),updateReQuest).enqueue(new Callback<UserResponse>() {
               @Override
               public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(ProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(ProfileActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
               }

               @Override
               public void onFailure(Call<UserResponse> call, Throwable t) {
                   Toast.makeText(ProfileActivity.this, "Failed", Toast.LENGTH_SHORT).show();
               }
           });
       }

    }

    private void addControls() {
        txtFullName = findViewById(R.id.txtFullName);
        txtDateOfBirth = findViewById(R.id.txtDateOfBirth);
        txtEmail = findViewById(R.id.txtEmail);
        rbFemale = findViewById(R.id.rbFemale);
        rbMale = findViewById(R.id.rbMale);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        attachData();
    }

    private void attachData() {


        if(user != null){
                txtFullName.setText(user.getName());
                txtEmail.setText(user.getEmail());
                txtDateOfBirth.setText(user.getBirthday());

                if(!user.getGender()){
                    rbMale.setChecked(true);
                }else{
                    rbFemale.setChecked(true);
                }

        }
    }
}
