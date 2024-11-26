package com.hcm.sale_laptop.data.model.other;

public class OrderSoldModel {
    private String id;
    private String title;
    private String price;
    private int quantity;
    private String date;

    public OrderSoldModel(String id, String title, String price, int quantity, String date) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }

    public String getOrderId() {
        return id;
    }

    public String getProductName() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }
}
