package com.shop.MyShop.entity;

public class Delivery {
    private long id;
    private String city;
    private String street;
    private int house_number;
    private int post_number;

    public Delivery(){
    }

    public Delivery(long id, String city, String street, int house_number, int post_number) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.house_number = house_number;
        this.post_number = post_number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse_number() {
        return house_number;
    }

    public void setHouse_number(int house_number) {
        this.house_number = house_number;
    }

    public int getPost_number() {
        return post_number;
    }

    public void setPost_number(int post_number) {
        this.post_number = post_number;
    }
}
