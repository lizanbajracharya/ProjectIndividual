package com.example.projectindividual.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectindividual.ProductActivity;
import com.example.projectindividual.R;
import com.example.projectindividual.Url.Url;
import com.example.projectindividual.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Product> productList;
    public static List <Product> selecteditems;
    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.cardveiw_item_book,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Product item=productList.get(position);
        holder.tv_book_title.setText(item.getItemName());
        Picasso.get().load(Url.base_url_image+productList.get(position).getItemImage()).into(holder.img_book_thumbnail);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductActivity.class);
                // passing data to the book activity
                intent.putExtra("id",productList.get(position).getId());
                intent.putExtra("Title",productList.get(position).getItemName());
                intent.putExtra("Description",productList.get(position).getItemDescription());
                intent.putExtra("Thumbnail",productList.get(position).getItemImage());
                intent.putExtra("Price",productList.get(position).getRate());
                intent.putExtra("Stock",productList.get(position).getQuantity());
                // start the activity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            TextView tv_book_title;
            ImageView img_book_thumbnail;
            CardView cardView ;
        public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_book_title =  itemView.findViewById(R.id.book_title_id) ;
                img_book_thumbnail =  itemView.findViewById(R.id.book_img_id);
                cardView =  itemView.findViewById(R.id.cardview_id);
            }
        }
    }
