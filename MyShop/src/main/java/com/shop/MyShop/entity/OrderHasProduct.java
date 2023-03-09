package com.shop.MyShop.entity;

public class OrderHasProduct {
    private CosmeticProduct cosmeticProduct;
    private int amount;
    private double price;

    public OrderHasProduct() {
    }

    public OrderHasProduct(CosmeticProduct cosmeticProduct, int amount, double price) {
        this.cosmeticProduct = cosmeticProduct;
        this.amount = amount;
        this.price = price;
    }

    public CosmeticProduct getCosmeticProduct() {
        return cosmeticProduct;
    }

    public void setCosmeticProduct(CosmeticProduct cosmeticProduct) {
        this.cosmeticProduct = cosmeticProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
