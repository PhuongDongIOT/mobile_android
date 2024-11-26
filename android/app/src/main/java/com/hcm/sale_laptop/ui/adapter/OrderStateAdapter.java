package com.hcm.sale_laptop.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcm.base.BaseAdapter;
import com.hcm.base.OnItemClick;
import com.hcm.sale_laptop.data.enums.OrderStatus;
import com.hcm.sale_laptop.data.model.other.OrderStateModel;
import com.hcm.sale_laptop.databinding.ItemConfirmOrderBinding;
import com.hcm.sale_laptop.utils.AppUtils;

import java.util.List;

public class OrderStateAdapter extends BaseAdapter<OrderStateModel, ItemConfirmOrderBinding> {

    private final OrderStatus orderStatus;
    private final boolean isHideCheckbox;
    private List<OrderStateModel> itemList;

    public OrderStateAdapter(List<OrderStateModel> itemList, OnItemClick<OrderStateModel> listener, OrderStatus status, boolean isHideCheckbox) {
        super(itemList, listener);
        this.itemList = itemList;
        this.orderStatus = status;
        this.isHideCheckbox = isHideCheckbox;
    }

    public void handlerRemoveItem(int position) {
        removeItem(position);
        resetCheckbox();
    }

    private void resetCheckbox() {
        for (OrderStateModel model : itemList) {
            model.setSelect(false);
        }
    }

    @Override
    protected ItemConfirmOrderBinding createBinding(LayoutInflater inflater, ViewGroup parent) {
        return ItemConfirmOrderBinding.inflate(inflater, parent, false);
    }

    @Override
    protected void bindData(OrderStateModel item, ItemConfirmOrderBinding binding, int position) {
        binding.tvAddress.setText(item.getAddress());
        binding.tvTrangThai.setText(orderStatus.getDescription());
        binding.tvMaDonHang.setText(item.getId());
        binding.rvProduct.setAdapter(new ItemProductAdapter(item.getProducts(), null));
        binding.tvTongTienHang.setText(AppUtils.customPrice(item.getPrices_order()));
        binding.tvTongTienPhiVanChuyen.setText("");
        binding.tvTongTienThanhToan.setText(AppUtils.customPrice(item.getPrices_order()));

        if (isHideCheckbox) {
            binding.checkbox.setVisibility(View.GONE);
        } else {
            binding.checkbox.setVisibility(View.VISIBLE);
            binding.checkbox.setChecked(item.isSelect());

            binding.checkbox.setOnClickListener(view -> {
                itemList = this.getItemList();

                for (OrderStateModel model : itemList) {
                    model.setSelect(false);
                }

                item.setSelect(binding.checkbox.isChecked());

                binding.getRoot().post(this::notifyDataSetChanged);
                final OnItemClick<OrderStateModel> onItemClick = getListener();
                if (onItemClick != null) {
                    item.setPosition(position);
                    onItemClick.onClick(item);
                }
            });
        }
    }
}
