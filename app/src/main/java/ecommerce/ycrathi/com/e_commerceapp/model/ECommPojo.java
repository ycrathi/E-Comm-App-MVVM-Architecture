package ecommerce.ycrathi.com.e_commerceapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ECommPojo  {

    @SerializedName("rankings")
    private ArrayList<Rankings> rankings;

    @SerializedName("categories")
    private ArrayList<Categories> categories;

    public ECommPojo(ArrayList<Rankings> rankings, ArrayList<Categories> categories) {
        this.rankings = rankings;
        this.categories = categories;
    }

    public ArrayList<Rankings> getRankings() {
        return rankings;
    }

    public void setRankings(ArrayList<Rankings> rankings) {
        this.rankings = rankings;
    }

    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "ClassPojo [rankings = " + rankings.toString() + ", categories = " + categories.toString() + "]";
    }
}