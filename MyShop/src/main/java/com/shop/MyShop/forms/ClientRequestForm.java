package com.shop.MyShop.forms;

public class ClientRequestForm {
    private long client_id;
    private long order_id;
    private long request_status_id;
    private String create_data;
    private String last_update_data;
    private long admin_id;

    public ClientRequestForm() {
    }

    public ClientRequestForm(long client_id, long order_id, long request_status_id, String create_data, String last_update_data, long admin_id) {
        this.client_id = client_id;
        this.order_id = order_id;
        this.request_status_id = request_status_id;
        this.create_data = create_data;
        this.last_update_data = last_update_data;
        this.admin_id = admin_id;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public long getRequest_status_id() {
        return request_status_id;
    }

    public void setRequest_status_id(long request_status_id) {
        this.request_status_id = request_status_id;
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

    public long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(long admin_id) {
        this.admin_id = admin_id;
    }
}
