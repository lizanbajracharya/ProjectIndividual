package com.example.projectindividual.Dashboard.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.projectindividual.R;
import com.example.projectindividual.SearchActivity;
import com.example.projectindividual.Url.Url;
import com.example.projectindividual.adapter.BannerAdapter;
import com.example.projectindividual.adapter.CategoryAdapter;
import com.example.projectindividual.adapter.ProductAdapter;
import com.example.projectindividual.api.CategoryApi;
import com.example.projectindividual.api.ProductApi;
import com.example.projectindividual.model.Category;
import com.example.projectindividual.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    ViewPager viewPage;
    String categoryid;
    ImageView img;
    RecyclerView recyclerView,recyclerViewCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_dashboard, container, false);
        viewPage = root.findViewById(R.id.viewPage);
        BannerAdapter viewPagerAdapter = new BannerAdapter(getActivity());
        viewPage.setAdapter(viewPagerAdapter);
        recyclerView = root.findViewById(R.id.recyclerViewAll);
        img=root.findViewById(R.id.ivSearch);
        recyclerViewCategory=root.findViewById(R.id.recyclerViewCategory);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        getCategory();
        getProduct();
        return root;
    }

    public void getCategory(){
        CategoryApi categoryApi = Url.getInstance().create(CategoryApi.class);
        Call<List<Category>> listCall =categoryApi.getCategory();
        listCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Toast" + response.code(), Toast.LENGTH_SHORT).show();
                }
                CategoryAdapter categoryAdapter =new CategoryAdapter(getActivity(),response.body());
                recyclerViewCategory.setAdapter(categoryAdapter);
                LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewCategory.setLayoutManager(layoutManager);
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getProduct(){
        ProductApi productApi= Url.getInstance().create(ProductApi.class);
        Call<List<Product>> listCall= productApi.getProduct();
        listCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Toast " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                ProductAdapter itemAdapter=new ProductAdapter(getActivity(),response.body());
                recyclerView.setAdapter(itemAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
