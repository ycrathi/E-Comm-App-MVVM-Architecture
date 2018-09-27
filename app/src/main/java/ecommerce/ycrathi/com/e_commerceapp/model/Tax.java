package ecommerce.ycrathi.com.e_commerceapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Tax implements Serializable {
    @SerializedName("name")
    private String name;

    @SerializedName("value")
    private float value;

    public Tax(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ClassPojo [name = " + name + ", value = " + value + "]";
    }
}
