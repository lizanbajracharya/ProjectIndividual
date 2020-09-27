package com.example.projectindividual.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectindividual.Dashboard.DashboardActivity;
import com.example.projectindividual.Dashboard.ui.Cart.CartFragment;
import com.example.projectindividual.ProductActivity;
import com.example.projectindividual.R;
import com.example.projectindividual.Url.Url;
import com.example.projectindividual.api.CartApi;
import com.example.projectindividual.api.FavoriteApi;
import com.example.projectindividual.model.Cart;
import com.example.projectindividual.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private Context context;
    private List<Cart> cartList;

    public CartAdapter(Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Cart cart=cartList.get(position);
        holder.tvIDD.setText(cart.getProduct().getId());
        holder.tvBookName.setText(cart.getProduct().getItemName());
        holder.tvCategory.setText(cart.getProduct().getRate());
        holder.tvWriter.setText("Rs."+cart.getProduct().getRate());
        Picasso.get().load(Url.base_url_image+cart.getProduct().getItemImage()).into(holder.imgView3);
        holder.imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartApi favoriteApi=Url.getInstance().create(CartApi.class);
                Call<Void> voidCall=favoriteApi.removeCart(cartList.get(position).getId());
                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(context, "You have remove an item", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context,DashboardActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(context, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvBookName,tvWriter,tvCategory,tvIDD;
        ImageButton imgRemove;
        ImageView imgView3;
        ConstraintLayout constraintlayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBookName =  itemView.findViewById(R.id.tvBook) ;
            tvCategory =  itemView.findViewById(R.id.tvCat);
            tvWriter =  itemView.findViewById(R.id.tvWrite);
            imgRemove=itemView.findViewById(R.id.imgRemove);
            imgView3=itemView.findViewById(R.id.imageView3);
            tvIDD=itemView.findViewById(R.id.tvIDD);
            constraintlayout=itemView.findViewById(R.id.contraintlayoutFavorite);
        }
    }

}
