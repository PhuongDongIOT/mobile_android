package com.hcm.sale_laptop.data.repository;

import com.hcm.sale_laptop.data.api.ApiService;
import com.hcm.sale_laptop.data.api.RetrofitClient;
import com.hcm.sale_laptop.data.model.other.PasswordRequestModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordRequestRepository {

    private final ApiService apiService;

    public PasswordRequestRepository() {
        // Khởi tạo Retrofit instance
        apiService = RetrofitClient.getInstance().create(ApiService.class);
    }

    /**
     * Gửi mật khẩu lên server.
     *
     * @param password       mật khẩu cần gửi.
     * @param callback       callback để xử lý kết quả trả về.
     */
    public void sendPassword(String password, ApiCallback callback) {
        PasswordRequestModel passwordRequest = new PasswordRequestModel(password);
        Call<Void> call = apiService.sendPassword(passwordRequest);

        // Thực hiện gọi API và xử lý phản hồi
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess("Password submitted successfully!");
                } else {
                    callback.onError("Failed to submit password. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Error: " + t.getMessage());
            }
        });
    }

    // Định nghĩa giao diện callback
    public interface ApiCallback {
        void onSuccess(String message);
        void onError(String error);
    }
}