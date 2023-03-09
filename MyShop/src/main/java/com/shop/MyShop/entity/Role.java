package com.shop.MyShop.entity;

public class Role {
    private long id;
    private String rolename;

    public Role() {
    }

    public Role(long id, String rolename) {
        this.id = id;
        this.rolename = rolename;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
