package com.example.projectindividual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectindividual.Url.Url;
import com.example.projectindividual.adapter.SearchAdapter;
import com.example.projectindividual.api.ProductApi;
import com.example.projectindividual.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    ImageButton imgBack,imgSearch;
    TextView tvSearch;
    RecyclerView recyclerView;
    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search);
        imgBack=findViewById(R.id.imgBack);
        imgSearch=findViewById(R.id.imgSearch);
        tvSearch=findViewById(R.id.tvSearch);
        recyclerView=findViewById(R.id.recyclerViewSearch);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductApi bookApi= Url.getInstance().create(ProductApi.class);
                Call<List<Product>> voidCall=bookApi.searchProduct(tvSearch.getText().toString());
                voidCall.enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(SearchActivity.this, "Toast"+response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        SearchAdapter itemAdapter=new SearchAdapter(getApplicationContext(),response.body());
                        recyclerView.setAdapter(itemAdapter);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        itemAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Toast.makeText(SearchActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}