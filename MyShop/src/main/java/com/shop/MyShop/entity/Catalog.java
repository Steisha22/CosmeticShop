package com.shop.MyShop.entity;

public class Catalog {
    private long id;
    private long catalog_id;
    private String name;

    public Catalog() {
    }

    public Catalog(long id, long catalog_id, String name) {
        this.id = id;
        this.catalog_id = catalog_id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCatalog_id() {
        return catalog_id;
    }

    public void setCatalog_id(long catalog_id) {
        this.catalog_id = catalog_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
