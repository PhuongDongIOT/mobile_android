package com.hcm.sale_laptop.data.model.other;

public class PasswordRequestModel {
    private String email;
    private String password;
    private String newPassword;
    private String reNewPassword;

    public PasswordRequestModel(String email, String password, String newPassword, String reNewPassword) {
        this.email = email;
        this.password = password;
        this.newPassword = newPassword;
        this.reNewPassword = reNewPassword;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
    }
}
