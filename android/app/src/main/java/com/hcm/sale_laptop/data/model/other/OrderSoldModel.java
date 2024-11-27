package com.hcm.sale_laptop.data.model.other;

public class OrderSoldModel {
    private String id;
    private String title;
    private String price;
    private String quantity;

    private String picture;
    private String date;

    public OrderSoldModel(String id, String title, String price, String quantity, String date, String picture) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
        this.picture = picture;
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

    public String getQuantity() {
        return quantity;
    }

    public String getPicture() {
        return picture;
    }

    public String getDate() {
        return date;
    }
}
