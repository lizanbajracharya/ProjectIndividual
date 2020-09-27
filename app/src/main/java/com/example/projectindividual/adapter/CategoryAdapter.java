package com.example.projectindividual.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectindividual.CategoryActivity;
import com.example.projectindividual.R;
import com.example.projectindividual.Url.Url;
import com.example.projectindividual.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.categoryAdapterViewHolder> {
    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public categoryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,null);
        return new categoryAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryAdapterViewHolder holder, int position) {

        final Category category=categoryList.get(position);
        holder.categoryName.setText(category.getCategoryName());
        Picasso.get().load(Url.category +categoryList.get(position).getImage()).into(holder.categoryImg);
        //Toast.makeText(context, ""+category.get_id(), Toast.LENGTH_SHORT).show();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CategoryActivity.class);
                intent.putExtra("categoryid",category.get_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class categoryAdapterViewHolder extends RecyclerView.ViewHolder{

        private ImageView categoryImg;
        private TextView categoryName;
        public categoryAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            this.categoryImg=itemView.findViewById(R.id.imgCategory);
            this.categoryName=itemView.findViewById(R.id.tvcategoryName);


        }



    }
}
