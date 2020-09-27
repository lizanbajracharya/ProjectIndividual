package com.example.projectindividual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectindividual.Dashboard.DashboardActivity;
import com.example.projectindividual.Url.Url;
import com.example.projectindividual.api.UserApi;
import com.example.projectindividual.bll.Edituserbll;
import com.example.projectindividual.model.User;
import com.example.projectindividual.strictmode.StrictModeClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    EditText etUsernameUpdate,etPasswordUpdate,etPhoneUpdate,etEmailUpdate;
    Button btnUpdate,btnCancel;
    TextView tvId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        etEmailUpdate=findViewById(R.id.etEmailChange);
        etPasswordUpdate=findViewById(R.id.etPasswordChange);
        etPhoneUpdate=findViewById(R.id.etMobileChange);
        etUsernameUpdate=findViewById(R.id.etUserchange);
        tvId=findViewById(R.id.tvId);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnCancel=findViewById(R.id.btnCancel);

        UserApi usersAPI = Url.getInstance().create(UserApi.class);
        final Call<User> userCall = usersAPI.getUserDetails(Url.token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    etUsernameUpdate.setText(response.body().getUsername());
                    etPhoneUpdate.setText(response.body().getMobileNumber());
                    etEmailUpdate.setText(response.body().getEmail());
                    etPasswordUpdate.setText(response.body().getPassword());
                    return;
                }
                else {
                    Toast.makeText(ProfileActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = etPhoneUpdate.getText().toString();
                String email = etEmailUpdate.getText().toString();
                String name = etUsernameUpdate.getText().toString();
                String password = etPasswordUpdate.getText().toString();
                User users = new User(number, password, email, name);
                Edituserbll edituserbll=new Edituserbll();
                StrictModeClass.StrictMode();

                if(edituserbll.Useredit(users)){
                    Toast.makeText(ProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ProfileActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ProfileActivity.this, "Error" , Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}