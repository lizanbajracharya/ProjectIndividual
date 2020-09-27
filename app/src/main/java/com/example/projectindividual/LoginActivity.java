package com.example.projectindividual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectindividual.Dashboard.DashboardActivity;
import com.example.projectindividual.bll.LoginBll;
import com.example.projectindividual.strictmode.StrictModeClass;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername,etPassword;
    Button btnLog,btnSignup;
    TextView tvForget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        btnSignup=findViewById(R.id.btnSignup);
        btnLog=findViewById(R.id.btnSign);
        etUsername=findViewById(R.id.etUser);
        etPassword=findViewById(R.id.etPass);
        tvForget=findViewById(R.id.text);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        if(TextUtils.isEmpty(etUsername.getText())){
            etUsername.setError("Enter Username");
        }
        else if(TextUtils.isEmpty(etPassword.getText())){
            etPassword.setError("Enter Password");
        }
        else{
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            LoginBll loginBLL = new LoginBll();

            StrictModeClass.StrictMode();
            if (loginBLL.checkUser(username, password)) {
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                SaveUsernamePassword();
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
                etUsername.requestFocus();
            }
        }
    }

    private void SaveUsernamePassword(){
        SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString("username",etUsername.getText().toString().trim());
        editor.putString("password",etPassword.getText().toString().trim());
        editor.commit();
    }
}
