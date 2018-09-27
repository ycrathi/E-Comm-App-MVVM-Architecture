package ecommerce.ycrathi.com.e_commerceapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import ecommerce.ycrathi.com.e_commerceapp.R;
import ecommerce.ycrathi.com.e_commerceapp.adapter.ProductAdapter;
import ecommerce.ycrathi.com.e_commerceapp.databinding.ActivityShowProductListingBinding;
import ecommerce.ycrathi.com.e_commerceapp.model.Categories;
import ecommerce.ycrathi.com.e_commerceapp.model.Products;
import ecommerce.ycrathi.com.e_commerceapp.model.SingleTonModel;
import ecommerce.ycrathi.com.e_commerceapp.viewmodel.ShowProductViewModel;

import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.INTENT_CAT_ID;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.INTENT_CLICKED_POSITION;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.INTENT_HANDLER;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.INTENT_HANDLER_PRODUCT;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.MOST_ORDERED_PRODUCTS;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.MOST_SHARED_PRODUCTS;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.MOST_VIEWED_PRODUCTS;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.SHOW_CATEGORY_PRODUCTS;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.SHOW_SUB_CATEGORY_PRODUCTS;

public class ShowProductListingActivity extends BaseActivity implements ProductAdapter.ProductAdapterListener {

    private RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    private ActivityShowProductListingBinding binding;
    private ArrayList<Products> productsArrayList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_show_product_listing);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_product_listing);


        int intentHandler = getIntent().getIntExtra(INTENT_HANDLER, 0);
        switch (intentHandler) {
            case SHOW_CATEGORY_PRODUCTS:
                int clickedPosition = getIntent().getIntExtra(INTENT_CLICKED_POSITION, 0);
                getSupportActionBar().setTitle("Products");
                productsArrayList = SingleTonModel.getInstance().geteCommPojo().getCategories().get(clickedPosition).getProducts();
                break;

            case SHOW_SUB_CATEGORY_PRODUCTS:

                int catId = getIntent().getIntExtra(INTENT_CAT_ID, 0);
                for (Categories cat : SingleTonModel.getInstance().geteCommPojo().getCategories()) {
                    if (catId == cat.getId()) {
                        productsArrayList = cat.getProducts();
                        break;
                    }
                }
                getSupportActionBar().setTitle("Products");
                break;
            case MOST_VIEWED_PRODUCTS:
                getSupportActionBar().setTitle("Most Viewed Products");
                productsArrayList = new ArrayList<>();
                ArrayList<Products> tempProductList = SingleTonModel.getInstance().geteCommPojo().getRankings().get(0).getProducts();
                HashMap<Integer, Products> tempMap = SingleTonModel.getInstance().getProductsHashMap();
                for (Products products : tempProductList) {
                    int id = products.getId();
                    Products originalProduct = tempMap.get(id);
                    originalProduct.setView_count(products.getView_count());
                    productsArrayList.add(originalProduct);
                }
                break;
            case MOST_ORDERED_PRODUCTS:
                getSupportActionBar().setTitle("Most Ordered Products");
                productsArrayList = new ArrayList<>();
                tempProductList = SingleTonModel.getInstance().geteCommPojo().getRankings().get(1).getProducts();
                tempMap = SingleTonModel.getInstance().getProductsHashMap();
                for (Products products : tempProductList) {
                    int id = products.getId();
                    Products originalProduct = tempMap.get(id);
                    originalProduct.setOrder_count(products.getOrder_count());
                    productsArrayList.add(originalProduct);
                }
                break;
            case MOST_SHARED_PRODUCTS:
                getSupportActionBar().setTitle("Most Shared Products");
                productsArrayList = new ArrayList<>();
                tempProductList = SingleTonModel.getInstance().geteCommPojo().getRankings().get(2).getProducts();
                tempMap = SingleTonModel.getInstance().getProductsHashMap();
                for (Products products : tempProductList) {
                    int id = products.getId();
                    Products originalProduct = tempMap.get(id);
                    originalProduct.setShares(products.getShares());
                    productsArrayList.add(originalProduct);
                }
                break;
        }

        ShowProductViewModel model = ViewModelProviders.of(this).get(ShowProductViewModel.class);

        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        mAdapter = new ProductAdapter(productsArrayList, this);
        recyclerView.setAdapter(mAdapter);

        binding.setProductModel(model);

    }

    @Override
    public void onProductClicked(Products products) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(INTENT_HANDLER_PRODUCT, products);
        startActivity(intent);
    }
}
