package com.example.projectindividual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectindividual.Url.Url;
import com.example.projectindividual.api.CartApi;
import com.example.projectindividual.api.UserApi;
import com.example.projectindividual.model.User;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView title,description,price,tvId;
    Button cart_button,btnBuy;
    private ImageView img;
    ImageButton imgbk;
    String Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_product);

        title=findViewById(R.id.booktitle);
        description=findViewById(R.id.tvdescription);
        price=findViewById(R.id.price);
        cart_button=findViewById(R.id.cart_button);
        tvId=findViewById(R.id.tvId);
        img=findViewById(R.id.fragranceImage);
        imgbk=findViewById(R.id.imgBk);
        btnBuy=findViewById(R.id.btnBuy);
        imgbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        final String productid=intent.getExtras().getString("id");
        final String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        String image = intent.getExtras().getString("Thumbnail") ;
        final String prices = intent.getExtras().getString("Price") ;
        Id=tvId.getText().toString();
        // Setting values

        title.setText("Product Name: "+Title);
        description.setText("Product Description: "+Description);
        price.setText("Rs. "+prices);
        Picasso.get().load(Url.base_url_image+image).into(img);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductActivity.this,PurchaseActivity.class);
                intent.putExtra("name",Title);
                intent.putExtra("rate",prices);
                startActivity(intent);
            }
        });
        cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartApi favoriteApi=Url.getInstance().create(CartApi.class);
                Call<Void> voidCall=favoriteApi.addcart(Url.token,productid);
                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(ProductActivity.this, "Successful added to cart", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

        UserApi usersAPI = Url.getInstance().create(UserApi.class);
        final Call<User> userCall = usersAPI.getUserDetails(Url.token);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    tvId.setText(Url.userid);
                    return;
                }
                else {
                    Toast.makeText(ProductActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ProductActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
