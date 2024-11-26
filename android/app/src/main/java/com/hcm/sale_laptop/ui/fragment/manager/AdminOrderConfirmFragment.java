package com.hcm.sale_laptop.ui.fragment.manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.hcm.base.BaseFragment;
import com.hcm.base.OnItemClick;
import com.hcm.sale_laptop.data.enums.OrderStatus;
import com.hcm.sale_laptop.data.model.other.OrderStateModel;
import com.hcm.sale_laptop.databinding.FragmentAdminOrderConfirmBinding;
import com.hcm.sale_laptop.ui.adapter.OrderStateAdapter;
import com.hcm.sale_laptop.ui.viewmodel.AdminConfirmOrderViewModel;
import com.hcm.sale_laptop.utils.AppUtils;

import java.util.ArrayList;

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
            if (orderStateModel == null) {
                showToast("Bạn chưa chọn đơn hàng nào để xác nhận");
                return;
            }

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

        mViewModel.getOrderData().observe(this, orderStateModels -> {
            final OrderStateAdapter adapter = (OrderStateAdapter) mBinding.recyclerView.getAdapter();
            if (adapter != null && AppUtils.checkListHasData(orderStateModels)) {
                adapter.setItems(orderStateModels);
            }
        });

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

    @Override
    public void onClick(OrderStateModel model) {
        this.orderStateModel = model.isSelect() ? model : null;
    }
}