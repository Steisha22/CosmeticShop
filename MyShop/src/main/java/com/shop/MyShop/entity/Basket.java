package com.shop.MyShop.entity;

public class Basket {
    private int id;
    private int client_id;
    private int total_amount;

    public Basket() {
    }

    public Basket(int id, int client_id, int total_amount) {
        this.id = id;
        this.client_id = client_id;
        this.total_amount = total_amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }
}
