package com.example.projectindividual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectindividual.bll.OrderBll;
import com.example.projectindividual.model.Order;
import com.example.projectindividual.strictmode.StrictModeClass;

public class PurchaseActivity extends AppCompatActivity {
    TextView tvProduct,tvRate;
    EditText etAddress,etMobile;
    Button btnOrder,btnCancel;
    String Address,Productname,Rate,Contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_purchase);

        tvProduct=findViewById(R.id.tvProduct);
        tvRate=findViewById(R.id.tvRate);
        etAddress=findViewById(R.id.etAddress);
        etMobile=findViewById(R.id.etPhone);
        btnOrder=findViewById(R.id.btnOrder);
        btnCancel=findViewById(R.id.btnCancellation);
        Intent intent=getIntent();
        Productname=intent.getExtras().getString("name");
        Rate=intent.getExtras().getString("rate");

        tvProduct.setText(Productname);
        tvRate.setText(Rate);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etAddress.getText())) {
                    etAddress.setError("Please Enter Address");
                    etAddress.requestFocus();
                } else if (TextUtils.isEmpty(etMobile.getText())) {
                    etMobile.setError("Please Enter Contact Information");
                    etMobile.requestFocus();
                } else {
                    Address = etAddress.getText().toString();
                    Contact = etMobile.getText().toString();
                    Order order = new Order(Productname, Rate, Address, Contact);

                    OrderBll orderBll=new OrderBll();
                    StrictModeClass.StrictMode();
                    if(orderBll.orderadd(order)){
                        Toast.makeText(PurchaseActivity.this, "Bought Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(PurchaseActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            }
        });
    }
}