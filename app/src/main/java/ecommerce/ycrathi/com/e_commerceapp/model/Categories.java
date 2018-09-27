package ecommerce.ycrathi.com.e_commerceapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Categories implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("child_categories")
    private ArrayList<Integer> child_categories;

    @SerializedName("products")
    private ArrayList<Products> products;

    public Categories(int id, String name, ArrayList<Integer> child_categories, ArrayList<Products> products) {
        this.id = id;
        this.name = name;
        this.child_categories = child_categories;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getChild_categories() {
        return child_categories;
    }

    public void setChild_categories(ArrayList<Integer> child_categories) {
        this.child_categories = child_categories;
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", name = " + name + ", child_categories = " + child_categories + ", products = " + products + "]";
    }
}
