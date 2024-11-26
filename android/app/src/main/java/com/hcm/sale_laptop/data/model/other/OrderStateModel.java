package com.hcm.sale_laptop.data.model.other;

import java.util.List;

public class OrderStateModel {
    private String name;
    private String avatar;
    private String address;
    private String review;
    private int is_deleted;
    private String id;
    private String status;
    private String summary;
    private int prices_order;
    private int sales_order;
    private List<ProductModel> products;
    private boolean isSelect;
    private int position;

    public OrderStateModel(String name, String avatar, String address, String review, int is_deleted,
                           String id, String status, String summary, int prices_order, int sales_order,
                           List<ProductModel> products, boolean isSelect) {
        this.name = name;
        this.avatar = avatar;
        this.address = address;
        this.review = review;
        this.is_deleted = is_deleted;
        this.id = id;
        this.status = status;
        this.summary = summary;
        this.prices_order = prices_order;
        this.sales_order = sales_order;
        this.products = products;
        this.isSelect = isSelect;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        this.isSelect = select;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getPrices_order() {
        return prices_order;
    }

    public void setPrices_order(int prices_order) {
        this.prices_order = prices_order;
    }

    public int getSales_order() {
        return sales_order;
    }

    public void setSales_order(int sales_order) {
        this.sales_order = sales_order;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
}
