package ecommerce.ycrathi.com.e_commerceapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import ecommerce.ycrathi.com.e_commerceapp.model.ECommPojo;
import ecommerce.ycrathi.com.e_commerceapp.model.Products;
import ecommerce.ycrathi.com.e_commerceapp.model.SingleTonModel;

public class ProductDetailsViewModel extends ViewModel implements ActionListener {


    public ObservableField<String> observableField = new ObservableField<>();
    private float productPrice;
    private Products products;
    private ActionListener listener;


    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float value) {
        this.productPrice = value;
        Log.d("Yogesh", "Price " + productPrice);
        observableField.set("₹ " + ((products.getTax().getValue() * (productPrice / 100)) + productPrice));
    }

    public void onClick(final View view) {
        listener.onClick(view);
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public void configureActionListener(ActionListener listener) {
        this.listener = listener;
    }

    public String getTaxText() {
        return "Tax -> Type " + products.getTax().getName() + " -> " + products.getTax().getValue() + "%";
    }
}
