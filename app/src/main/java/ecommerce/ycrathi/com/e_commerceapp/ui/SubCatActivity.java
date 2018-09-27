package ecommerce.ycrathi.com.e_commerceapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import ecommerce.ycrathi.com.e_commerceapp.R;
import ecommerce.ycrathi.com.e_commerceapp.adapter.SubCategoriesAdapter;
import ecommerce.ycrathi.com.e_commerceapp.databinding.ActivitySubCatBinding;
import ecommerce.ycrathi.com.e_commerceapp.model.Categories;
import ecommerce.ycrathi.com.e_commerceapp.viewmodel.SubCatViewModel;

import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.INTENT_CAT_ID;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.INTENT_HANDLER;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.INTENT_SUB_CAT_LIST;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.SHOW_SUB_CATEGORY_PRODUCTS;

public class SubCatActivity extends BaseActivity implements SubCategoriesAdapter.CategoriesAdapterListener {
    public RecyclerView recyclerView;
    public SubCategoriesAdapter mAdapter;
    ActivitySubCatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Select Subcategory");

        ArrayList<Categories> subCatList = (ArrayList<Categories>) getIntent().getSerializableExtra(INTENT_SUB_CAT_LIST);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sub_cat);
        SubCatViewModel model = ViewModelProviders.of(this).get(SubCatViewModel.class);
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        mAdapter = new SubCategoriesAdapter(subCatList, this);
        recyclerView.setAdapter(mAdapter);
        binding.setSubCatViewModel(model);
    }


    @Override
    public void onSubCatClicked(Categories categories, int clickedPosition) {
        Intent intent = new Intent(this, ShowProductListingActivity.class);
        intent.putExtra(INTENT_HANDLER, SHOW_SUB_CATEGORY_PRODUCTS);
        intent.putExtra(INTENT_CAT_ID, categories.getId());
        startActivity(intent);
    }
}
