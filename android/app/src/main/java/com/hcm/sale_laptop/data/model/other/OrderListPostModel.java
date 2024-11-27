package com.hcm.sale_laptop.data.model.other;

import java.util.List;

public class OrderListPostModel {
    private List<String> orderList;
    private int status;


    public OrderListPostModel(List<String> orderList, int status) {
        this.orderList = orderList;
        this.status = status;
    }
    public List<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<String>  orderList) {
        this.orderList = orderList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
