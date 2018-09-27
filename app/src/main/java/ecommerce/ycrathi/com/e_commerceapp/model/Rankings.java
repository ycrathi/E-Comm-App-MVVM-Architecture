package ecommerce.ycrathi.com.e_commerceapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Rankings {
    @SerializedName("products")
    private ArrayList<Products> products;

    @SerializedName("ranking")
    private String ranking;

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "ClassPojo [products = " + products.toString() + ", ranking = " + ranking + "]";
    }
}
