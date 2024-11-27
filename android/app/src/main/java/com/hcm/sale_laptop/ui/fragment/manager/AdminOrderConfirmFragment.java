package com.hcm.sale_laptop.ui.fragment.manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.hcm.base.BaseFragment;
import com.hcm.base.OnItemClick;
import com.hcm.sale_laptop.data.api.ApiService;
import com.hcm.sale_laptop.data.api.RetrofitClient;
import com.hcm.sale_laptop.data.enums.OrderStatus;
import com.hcm.sale_laptop.data.model.other.OrderListPostModel;
import com.hcm.sale_laptop.data.model.other.OrderStateModel;
import com.hcm.sale_laptop.databinding.FragmentAdminOrderConfirmBinding;
import com.hcm.sale_laptop.ui.adapter.OrderStateAdapter;
import com.hcm.sale_laptop.ui.viewmodel.AdminConfirmOrderViewModel;
import com.hcm.sale_laptop.utils.AppUtils;
import com.hcm.sale_laptop.utils.ConstantsList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminOrderConfirmFragment extends BaseFragment<AdminConfirmOrderViewModel, FragmentAdminOrderConfirmBinding> implements OnItemClick<OrderStateModel> {

    private OrderStateModel orderStateModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentAdminOrderConfirmBinding.inflate(inflater, container, false);
        setup();
        return mBinding.getRoot();
    }

    @Override
    protected void setupUI() {
        final OrderStateAdapter adapter = new OrderStateAdapter(new ArrayList<>(), this, OrderStatus.PENDING_CONFIRMATION, false);
        mBinding.recyclerView.setAdapter(adapter);

    }


    @Override
    protected void setupAction() {
        setOnClickListener(mBinding.btnConfirmOrder, view -> {
            this.postCancelOder();
        });
    }


    @Override
    protected void setupData() {
        mViewModel = new AdminConfirmOrderViewModel();

        mViewModel.getOrderAll();

        mViewModel.errorMessage.observe(this, this::showToast);

        mViewModel.isLoading.observe(this, isLoading -> {
            if (isLoading) {
                showProgressBar();
            } else {
                hideProgressBar();
            }
        });

        this.postOder();

        mViewModel.getIsConfirmOrderSuccess().observe(this, isSuccess -> {
            if (isSuccess) {
                final OrderStateAdapter adapter = (OrderStateAdapter) mBinding.recyclerView.getAdapter();
                if (adapter != null) {
                    adapter.handlerRemoveItem(orderStateModel.getPosition());
                    orderStateModel = null;
                }
                showToast("Xác nhận đơn hàng thành công");
            } else {
                showToast("Xác nhận đơn hàng thất bại");
            }
        });
    }
    private void postCancelOder () {
        OrderListPostModel orderListPost = new OrderListPostModel(ConstantsList.getOrderStateSelect(), 0);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Void> call = apiService.sendOptionOrder(orderListPost);
        call.enqueue(new retrofit2.Callback<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    postOder();
                    Toast.makeText(getActivity(), "Succeed to fetch confirm rders", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Failed to fetch confirm orders", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void postOder () {
        mViewModel.getOrderData().observe(this, orderStateModels -> {
            final OrderStateAdapter adapter = (OrderStateAdapter) mBinding.recyclerView.getAdapter();
            if (adapter != null && AppUtils.checkListHasData(orderStateModels)) {
                adapter.setItems(orderStateModels);
            }
        });
    }


    @Override
    public void onClick(OrderStateModel model) {
        this.orderStateModel = model.isSelect() ? model : null;
    }
}