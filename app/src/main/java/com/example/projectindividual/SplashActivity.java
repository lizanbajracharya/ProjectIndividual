package com.example.projectindividual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.projectindividual.Dashboard.DashboardActivity;
import com.example.projectindividual.bll.LoginBll;
import com.example.projectindividual.strictmode.StrictModeClass;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUser();
            }
        },2000);
    }
    private void checkUser(){
        SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        String username=sharedPreferences.getString("username","");
        String password=sharedPreferences.getString("password","");
        LoginBll loginBll=new LoginBll();
        StrictModeClass.StrictMode();
        if(loginBll.checkUser(username, password)){
            Intent intent=new Intent(SplashActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent=new Intent(SplashActivity.this,IntroActivity.class);
            startActivity(intent);
            finish();
        }
    }

}