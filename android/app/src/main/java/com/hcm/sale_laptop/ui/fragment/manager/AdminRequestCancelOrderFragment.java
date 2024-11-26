package com.hcm.sale_laptop.ui.fragment.manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.hcm.base.BaseFragment;
import com.hcm.sale_laptop.data.api.ApiService;
import com.hcm.sale_laptop.data.api.RetrofitClient;
import com.hcm.sale_laptop.data.model.other.OrderStateModel;
import com.hcm.sale_laptop.databinding.FragmentAdminRequestCancelOrderBinding;
import com.hcm.sale_laptop.ui.adapter.RequestCancelOrderAdapter;
import com.hcm.sale_laptop.ui.viewmodel.AdminRequestCancelOrderViewModel;
import com.hcm.sale_laptop.utils.AppUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminRequestCancelOrderFragment extends BaseFragment<AdminRequestCancelOrderViewModel, FragmentAdminRequestCancelOrderBinding> {

    private OrderStateModel orderStateModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentAdminRequestCancelOrderBinding.inflate(inflater, container, false);
        setup();
        return mBinding.getRoot();
    }

    @Override
    protected void setupUI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.BASE_URL + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<OrderStateModel>> call = apiService.getRequestOrders();
        call.enqueue(new retrofit2.Callback<List<OrderStateModel>>() {
            public void onResponse(Call<List<OrderStateModel>> call, Response<List<OrderStateModel>> response) {
                if (response.isSuccessful()) {
                    List<OrderStateModel> orders = response.body();
                    final RequestCancelOrderAdapter adapter = new RequestCancelOrderAdapter(orders, null);
                    mBinding.recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Failed to fetch orders", Toast.LENGTH_SHORT).show();
                }
            }


            public void onFailure(Call<List<OrderStateModel>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void setupAction() {

    }

    @Override
    protected void setupData() {
        mViewModel = new AdminRequestCancelOrderViewModel();

        mViewModel.getDataOrdersCancel();

        mViewModel.errorMessage.observe(this, this::showToast);

        mViewModel.isLoading.observe(this, isLoading -> {
            if (isLoading) {
                showProgressBar();
            } else {
                hideProgressBar();
            }
        });

        mViewModel.getOrderData().observe(this, orderStateModels -> {
            final RequestCancelOrderAdapter adapter = (RequestCancelOrderAdapter) mBinding.recyclerView.getAdapter();
            if (adapter != null && AppUtils.checkListHasData(orderStateModels)) {
                adapter.setItems(orderStateModels);
            }
        });

        mViewModel.getIsConfirmOrderSuccess().observe(this, isSuccess -> {
            if (isSuccess) {
                final RequestCancelOrderAdapter adapter = (RequestCancelOrderAdapter) mBinding.recyclerView.getAdapter();
                if (adapter != null) {
                    adapter.handlerRemoveItem(orderStateModel.getPosition());
                    orderStateModel = null;
                }
                showToast("Hủy đơn hàng thành công");
            } else {
                showToast("Hủy đơn hàng thất bại");
            }
        });
    }
}