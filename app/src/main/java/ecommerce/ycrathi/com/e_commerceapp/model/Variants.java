package ecommerce.ycrathi.com.e_commerceapp.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Variants implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("price")
    private float price;

    @SerializedName("color")
    private String color;

    @SerializedName("size")
    private int size;

    public Variants(int id, float price, String color, int size) {
        this.id = id;
        this.price = price;
        this.color = color;
        this.size = size;
    }

    public String getSizeText() {
        if (size == 0) {
            return "Size = NA";
        } else {
            return "Size = " + size;
        }

    }

    public String getPriceText() {
        return "₹ " + price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", price = " + price + ", color = " + color + ", size = " + size + "]";
    }
}