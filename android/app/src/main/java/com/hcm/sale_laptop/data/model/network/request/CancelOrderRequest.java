package com.hcm.sale_laptop.data.model.network.request;

public class CancelOrderRequest {

    private String order_id;
    private String reason;

    public CancelOrderRequest(String order_id, String reason) {
        this.order_id = order_id;
        this.reason = reason;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
