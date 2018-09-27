package ecommerce.ycrathi.com.e_commerceapp.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Products implements Serializable {

    @SerializedName("id")
    private int id;
    private int currentPosition;
    @SerializedName("view_count")
    private int view_count;
    @SerializedName("order_count")
    private int order_count;
    @SerializedName("tax")
    private Tax tax;
    @SerializedName("date_added")
    private String date_added;
    @SerializedName("name")
    private String name;
    @SerializedName("shares")
    private int shares;
    @SerializedName("variants")
    private ArrayList<Variants> variants;

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Variants> getVariants() {
        return variants;
    }

    public void setVariants(ArrayList<Variants> variants) {
        this.variants = variants;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", tax = " + tax + ", date_added = " + date_added + ", name = " + name + ", variants = " + variants + "]";
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }
}