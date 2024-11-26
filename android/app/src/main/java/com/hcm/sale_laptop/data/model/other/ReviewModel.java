package com.hcm.sale_laptop.data.model.other;

public class ReviewModel {
    private String orderId;
    private String orderDate;
    private String productName;
    private String customerName;
    private String reviewText;
    private float rating;

    // Constructor
    public ReviewModel(String orderId, String orderDate, String productName, String customerName, String reviewText, float rating) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.productName = productName;
        this.customerName = customerName;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getProductName() {
        return productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public float getRating() {
        return rating;
    }
}
