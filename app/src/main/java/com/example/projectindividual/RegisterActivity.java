package com.example.projectindividual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectindividual.Url.Url;
import com.example.projectindividual.api.UserApi;
import com.example.projectindividual.bll.SignupBll;
import com.example.projectindividual.model.User;
import com.example.projectindividual.serverresponse.SignUpResponse;
import com.example.projectindividual.strictmode.StrictModeClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Button btnLogin,btnSign;
    EditText etPass,etUser,etNumber,etEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        btnLogin=findViewById(R.id.btnLogin);
        btnSign=findViewById(R.id.btnSign);
        etPass=findViewById(R.id.etPass);
        etUser=findViewById(R.id.etUser);
        etNumber=findViewById(R.id.etNumber);
        etEmail=findViewById(R.id.etEmail);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etUser.getText())) {
                    etUser.setError("Enter Username");
                    etUser.setFocusable(true);
                } else if (TextUtils.isEmpty(etPass.getText())) {
                    etPass.setError("Enter Password");
                } else if (TextUtils.isEmpty(etNumber.getText())) {
                    etNumber.setError("Enter Mobile Number");
                } else if (TextUtils.isEmpty(etEmail.getText())) {
                    etEmail.setError("Enter Email");
                } else {
                    String number = etNumber.getText().toString();
                    String email = etEmail.getText().toString();
                    String name = etUser.getText().toString();
                    String password = etPass.getText().toString();

                    User users = new User(number, password, email, name);

                    SignupBll signupBll=new SignupBll();
                    StrictModeClass.StrictMode();
                    if(signupBll.Useradd(users)){
                        Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
//                    UserApi usersAPI = Url.getInstance().create(UserApi.class);
//                    Call<SignUpResponse> signUpCall = usersAPI.registerUser(users);
//
//                    signUpCall.enqueue(new Callback<SignUpResponse>() {
//                        @Override
//                        public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
//                            if (!response.isSuccessful()) {
//                                Toast.makeText(RegisterActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                            Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<SignUpResponse> call, Throwable t) {
//                            Toast.makeText(RegisterActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
                    etEmail.getText().clear();
                    etPass.getText().clear();
                    etNumber.getText().clear();
                    etUser.getText().clear();
                }
            }
        });
    }
}
