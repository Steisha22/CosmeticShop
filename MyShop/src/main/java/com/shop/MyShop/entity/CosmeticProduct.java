package com.shop.MyShop.entity;

public class CosmeticProduct {
    private long id;
    private String name;
    private double price;
    private String marka;
    private int amount;
    private Catalog catalog; //int catalog_id
    private String decription;

    public CosmeticProduct() {
    }

    public CosmeticProduct(long id, String name, double price, String marka, int amount, Catalog catalog, String decription) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.marka = marka;
        this.amount = amount;
        this.catalog = catalog;
        this.decription = decription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
