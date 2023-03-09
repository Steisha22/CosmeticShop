package com.shop.MyShop.forms;

public class UpdateStatusForm {
    private long statusId;
    //private long client_id;
    private long id;
    private String data;

    public UpdateStatusForm() {
    }

    public UpdateStatusForm(long statusId, long id, String data) {
        this.statusId = statusId;
        this.id = id;
        this.data = data;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
