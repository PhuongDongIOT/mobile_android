package com.hcm.sale_laptop.data.model.other;

public class ResetPasswordModel {
    private String email;

    public ResetPasswordModel(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
