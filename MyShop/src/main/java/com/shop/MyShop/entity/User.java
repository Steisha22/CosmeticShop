package com.shop.MyShop.entity;

public class User {
    private long id;
    private String username;
    private String password;
    private int amount_of_orders;
    private Role role;
    private String phone;
    private String email;
    private String first_name;
    private String last_name;

    public User() {
    }

    public User(long id, String username, String password, int amount_of_orders, Role role, String phone,
                String email, String first_name, String last_name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.amount_of_orders = amount_of_orders;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAmount_of_orders() {
        return amount_of_orders;
    }

    public void setAmount_of_orders(int amount_of_orders) {
        this.amount_of_orders = amount_of_orders;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}