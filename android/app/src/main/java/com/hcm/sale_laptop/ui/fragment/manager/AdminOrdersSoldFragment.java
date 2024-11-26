package com.hcm.sale_laptop.ui.fragment.manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.data.api.ApiService;
import com.hcm.sale_laptop.data.api.RetrofitClient;
import com.hcm.sale_laptop.data.model.other.OrderSoldModel;
import com.hcm.sale_laptop.ui.adapter.AdminOrderSoldAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminOrdersSoldFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdminOrderSoldAdapter adminOrderSoldAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_soulds, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fetchOrders();

        return view;
    }

    private void fetchOrders() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.BASE_URL + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<OrderSoldModel>> call = apiService.getOrdersSold();

        call.enqueue(new Callback<List<OrderSoldModel>>() {
            @Override
            public void onResponse(Call<List<OrderSoldModel>> call, Response<List<OrderSoldModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<OrderSoldModel> orders = response.body();
                    adminOrderSoldAdapter = new AdminOrderSoldAdapter(orders);
                    recyclerView.setAdapter(adminOrderSoldAdapter);
                } else {
                    Toast.makeText(getContext(), "Không tải được dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<OrderSoldModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi API: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}