package ecommerce.ycrathi.com.e_commerceapp.model;

import java.util.HashMap;

public class SingleTonModel {

    private static final SingleTonModel ourInstance = new SingleTonModel();
    private ECommPojo eCommPojo;
    private HashMap<Integer, Products> productsHashMap = new HashMap<>();

    private SingleTonModel() {
    }

    public static SingleTonModel getInstance() {
        return ourInstance;
    }

    public HashMap<Integer, Products> getProductsHashMap() {
        return productsHashMap;
    }

    public ECommPojo geteCommPojo() {
        return eCommPojo;
    }

    public void seteCommPojo(ECommPojo eCommPojo) {
        this.eCommPojo = eCommPojo;
    }

    public void addProduct(int id, Products products) {
        productsHashMap.put(id, products);
    }
}
