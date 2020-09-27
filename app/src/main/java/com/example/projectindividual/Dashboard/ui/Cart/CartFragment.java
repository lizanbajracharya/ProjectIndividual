package com.example.projectindividual.Dashboard.ui.Cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectindividual.R;
import com.example.projectindividual.Url.Url;
import com.example.projectindividual.adapter.CartAdapter;
import com.example.projectindividual.adapter.ProductAdapter;
import com.example.projectindividual.api.CartApi;
import com.example.projectindividual.model.Cart;
import com.example.projectindividual.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    public static int total=0;
    String jsonCartList;
    CartAdapter itemAdapter;
    ProductAdapter ItemListAdapter;
    public static TextView tv_total;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.cart_recycler);
        tv_total=root.findViewById(R.id.totalPrice);
        getProduct();
        calculateTotal();
        return root;
    }

    private void getProduct(){
        CartApi favoriteApi= Url.getInstance().create(CartApi.class);
        Call<List<Cart>> listCall= favoriteApi.getCart(Url.token);
        listCall.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Toast " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                itemAdapter=new CartAdapter(getActivity(),response.body());
                recyclerView.setAdapter(itemAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void calculateTotal(){
        List<Cart> item=new ArrayList<>();
        total = 0;
        for(int i = 0; i < item.size(); i++){
            total = total + Integer.parseInt(item.get(i).getProduct().getRate());
        }
        tv_total.setText(String.valueOf( total));
    }
}
