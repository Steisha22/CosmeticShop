package com.shop.MyShop.entity;

import java.util.*;

public class Order {
    private long id;
    private String create_data;
    private String last_update;
    private Status status;
    private Delivery delivery;
    private User client;
    private User admin;
    private int total_amount;
    private double total_sum;
    private ArrayList<OrderHasProduct> list_of_products;

    public Order (){
        //list_of_products = new List<CosmeticProduct>();
    }

    public Order(long id, String create_data, String last_update, Status status, Delivery delivery, User client, User admin, int total_amount, double total_sum, ArrayList<OrderHasProduct> list_of_products) {
        this.id = id;
        this.create_data = create_data;
        this.last_update = last_update;
        this.status = status;
        this.delivery = delivery;
        this.client = client;
        this.admin = admin;
        this.total_amount = total_amount;
        this.total_sum = total_sum;
        this.list_of_products = list_of_products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreate_data() {
        return create_data;
    }

    public void setCreate_data(String create_data) {
        this.create_data = create_data;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public double getTotal_sum() {
        return total_sum;
    }

    public void setTotal_sum(double total_sum) {
        this.total_sum = total_sum;
    }

    public ArrayList<OrderHasProduct> getList_of_products() {
        return list_of_products;
    }

    public void setList_of_products(ArrayList<OrderHasProduct> list_of_products) {
        this.list_of_products = list_of_products;
    }

    /*public Order(long id, String create_data, String last_update, Status status, Delivery delivery, User client, User admin, int total_amount, double total_sum, ArrayList<CosmeticProduct> list_of_products) {
        this.id = id;
        this.create_data = create_data;
        this.last_update = last_update;
        this.status = status;
        this.delivery = delivery;
        this.client = client;
        this.admin = admin;
        this.total_amount = total_amount;
        this.total_sum = total_sum;
        this.list_of_products = list_of_products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreate_data() {
        return create_data;
    }

    public void setCreate_data(String create_data) {
        this.create_data = create_data;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public double getTotal_sum() {
        return total_sum;
    }

    public void setTotal_sum(double total_sum) {
        this.total_sum = total_sum;
    }

    public List<CosmeticProduct> getList_of_products() {
        return list_of_products;
    }

    public void setItem(CosmeticProduct item) {
        list_of_products.add(item);
    }*/
}
