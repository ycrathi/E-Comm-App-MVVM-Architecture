package ecommerce.ycrathi.com.e_commerceapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import ecommerce.ycrathi.com.e_commerceapp.R;
import ecommerce.ycrathi.com.e_commerceapp.adapter.VariantAdapter;
import ecommerce.ycrathi.com.e_commerceapp.databinding.ActivityProductDetailsBinding;
import ecommerce.ycrathi.com.e_commerceapp.model.Products;
import ecommerce.ycrathi.com.e_commerceapp.model.Variants;
import ecommerce.ycrathi.com.e_commerceapp.viewmodel.ActionListener;
import ecommerce.ycrathi.com.e_commerceapp.viewmodel.ProductDetailsViewModel;

import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.INTENT_HANDLER_PRODUCT;

public class ProductDetailsActivity extends BaseActivity implements VariantAdapter.VariantAdapterListener, ActionListener {

    private ProductDetailsViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_product_details);
        ActivityProductDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details);

        Products products = (Products) getIntent().getSerializableExtra(INTENT_HANDLER_PRODUCT);

        getSupportActionBar().setTitle(products.getName());
        model = ViewModelProviders.of(this).get(ProductDetailsViewModel.class);
        model.configureActionListener(this);
        model.setProducts(products);
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        VariantAdapter mAdapter = new VariantAdapter(products.getVariants(), this);
        recyclerView.setAdapter(mAdapter);

        //binding.productFinalPrice.
        binding.setProductDetailsModel(model);

        Log.d("Yogesh", "Product " + products.toString());
    }

    @Override
    public void onVariantClicked(Variants variants) {
        model.setProductPrice(variants.getPrice());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buyNowTv:
                Toast.makeText(this, "Buy Now", Toast.LENGTH_SHORT).show();
                break;
            case R.id.addToCartTV:
                Toast.makeText(this, "Add to Cart", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
