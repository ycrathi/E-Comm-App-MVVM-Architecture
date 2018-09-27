package ecommerce.ycrathi.com.e_commerceapp.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import ecommerce.ycrathi.com.e_commerceapp.R;
import ecommerce.ycrathi.com.e_commerceapp.databinding.CatRowItemBinding;
import ecommerce.ycrathi.com.e_commerceapp.databinding.ProductRowItemBinding;
import ecommerce.ycrathi.com.e_commerceapp.model.Categories;
import ecommerce.ycrathi.com.e_commerceapp.model.ECommPojo;
import ecommerce.ycrathi.com.e_commerceapp.model.Products;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private ArrayList<Products> productsArrayList;
    private LayoutInflater layoutInflater;
    private ProductAdapterListener listener;

    public ProductAdapter(ArrayList<Products> productsArrayList, ProductAdapterListener listener) {
        this.productsArrayList = productsArrayList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ProductRowItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.product_row_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.binding.setProductRow(productsArrayList.get(position));
        holder.binding.productRowLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onProductClicked(productsArrayList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    public interface ProductAdapterListener {
        void onProductClicked(Products products);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ProductRowItemBinding binding;

        public MyViewHolder(final ProductRowItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}