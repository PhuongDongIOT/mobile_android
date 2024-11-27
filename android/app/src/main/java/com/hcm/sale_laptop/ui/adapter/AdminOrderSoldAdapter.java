package com.hcm.sale_laptop.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.data.model.other.OrderSoldModel;
import com.hcm.sale_laptop.utils.AppUtils;

import java.util.List;

public class AdminOrderSoldAdapter extends RecyclerView.Adapter<AdminOrderSoldAdapter.OrderViewHolder> {
    private final List<OrderSoldModel> orderList;

    public AdminOrderSoldAdapter(List<OrderSoldModel> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_sold, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderSoldModel order = orderList.get(position);
        holder.tvOrderId.setText(order.getOrderId());
        holder.tvProductName.setText(order.getProductName());
        holder.tvPrice.setText(order.getPrice() + " VND");
        holder.tvDetails.setText("Số lượng: " + order.getQuantity());
        holder.tvNgay.setText(order.getDate());
        // Set hình ảnh nếu cần (ở đây dùng ảnh mặc định)
        AppUtils.loadImageUrl( holder.orderImage, order.getPicture());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId, tvProductName, tvPrice, tvDetails, tvNgay;
        ImageView orderImage;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvMaDonHang);
            tvProductName = itemView.findViewById(R.id.tvNameProduct);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDetails = itemView.findViewById(R.id.tvQuantity);
            tvNgay = itemView.findViewById(R.id.tvNgay);
            orderImage = itemView.findViewById(R.id.orderImage);
        }
    }
}
