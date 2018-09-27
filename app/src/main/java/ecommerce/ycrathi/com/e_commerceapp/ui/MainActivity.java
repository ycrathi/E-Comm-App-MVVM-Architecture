package ecommerce.ycrathi.com.e_commerceapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import ecommerce.ycrathi.com.e_commerceapp.R;
import ecommerce.ycrathi.com.e_commerceapp.adapter.CategoriesAdapter;
import ecommerce.ycrathi.com.e_commerceapp.databinding.ActivityMainBinding;
import ecommerce.ycrathi.com.e_commerceapp.model.Categories;
import ecommerce.ycrathi.com.e_commerceapp.model.SingleTonModel;
import ecommerce.ycrathi.com.e_commerceapp.viewmodel.ActionListener;
import ecommerce.ycrathi.com.e_commerceapp.viewmodel.MainViewModel;

import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.INTENT_CLICKED_POSITION;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.INTENT_HANDLER;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.INTENT_SUB_CAT_LIST;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.MOST_ORDERED_PRODUCTS;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.MOST_SHARED_PRODUCTS;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.MOST_VIEWED_PRODUCTS;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.SHOW_CATEGORY_PRODUCTS;
import static ecommerce.ycrathi.com.e_commerceapp.model.Constants.SHOW_SUB_CATEGORY;

public class MainActivity extends BaseActivity implements CategoriesAdapter.CategoriesAdapterListener, ActionListener {
    public RecyclerView recyclerView;
    public CategoriesAdapter mAdapter;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getDataFromAPI();

    }

    private void getDataFromAPI() {

        MainViewModel model = ViewModelProviders.of(this).get(MainViewModel.class);

        model.configureActionListener(this);
        model.getEcommData(retrofit).observe(this, data -> {
            // update UI

            if (data == null) {
                showNetworkError();
                return;
            }


            recyclerView = binding.recyclerView;
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setHasFixedSize(true);
            mAdapter = new CategoriesAdapter(data, this);
            recyclerView.setAdapter(mAdapter);
            SingleTonModel.getInstance().seteCommPojo(data);

        });

        binding.setMainViewModel(model);
    }

    private void showNetworkError() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(MainActivity.this);
        }
        builder.setTitle("Network Error")
                .setMessage("Please try after some time or Do You want to retry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        getDataFromAPI();
                    }
                }).setIcon(android.R.drawable.ic_dialog_alert)
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

    @Override
    public void onCatClicked(Categories categories, int clickedPosition) {
        Intent intent = new Intent(this, ShowProductListingActivity.class);
        intent.putExtra(INTENT_HANDLER, SHOW_CATEGORY_PRODUCTS);
        intent.putExtra(INTENT_CLICKED_POSITION, clickedPosition);
        startActivity(intent);
    }


    @Override
    public void showSubCat(ArrayList<Categories> subCatList) {
        Intent intent = new Intent(this, SubCatActivity.class);
        intent.putExtra(INTENT_HANDLER, SHOW_SUB_CATEGORY);
        intent.putExtra(INTENT_SUB_CAT_LIST, subCatList);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mostViewedTV:
                Intent intent = new Intent(this, ShowProductListingActivity.class);
                intent.putExtra(INTENT_HANDLER, MOST_VIEWED_PRODUCTS);
                startActivity(intent);
                break;
            case R.id.mostOrderedTV:
                intent = new Intent(this, ShowProductListingActivity.class);
                intent.putExtra(INTENT_HANDLER, MOST_ORDERED_PRODUCTS);
                startActivity(intent);
                break;
            case R.id.mostSharedTV:
                intent = new Intent(this, ShowProductListingActivity.class);
                intent.putExtra(INTENT_HANDLER, MOST_SHARED_PRODUCTS);
                startActivity(intent);
                break;
        }
    }
}
