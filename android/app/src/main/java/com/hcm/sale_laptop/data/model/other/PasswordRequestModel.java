package com.hcm.sale_laptop.data.model.other;

public class PasswordRequestModel {
    private String password;

    public PasswordRequestModel(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
