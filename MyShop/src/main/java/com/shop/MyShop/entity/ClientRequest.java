package com.shop.MyShop.entity;

public class ClientRequest {
    private long id;
    private User client;
    private Order order;
    private RequestStatus request_status;
    private String create_data;
    private String last_update_data;
    private User admin;

    public ClientRequest() {
    }

    public ClientRequest(long id, User client, Order order, RequestStatus request_status, String create_data, String last_update_data, User admin) {
        this.id = id;
        this.client = client;
        this.order = order;
        this.request_status = request_status;
        this.create_data = create_data;
        this.last_update_data = last_update_data;
        this.admin = admin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public RequestStatus getRequest_status() {
        return request_status;
    }

    public void setRequest_status(RequestStatus request_status) {
        this.request_status = request_status;
    }

    public String getCreate_data() {
        return create_data;
    }

    public void setCreate_data(String create_data) {
        this.create_data = create_data;
    }

    public String getLast_update_data() {
        return last_update_data;
    }

    public void setLast_update_data(String last_update_data) {
        this.last_update_data = last_update_data;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
