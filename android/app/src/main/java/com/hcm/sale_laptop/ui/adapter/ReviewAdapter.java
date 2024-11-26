package com.hcm.sale_laptop.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.data.model.other.ReviewModel;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private final List<ReviewModel> reviews;

    public ReviewAdapter(List<ReviewModel> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        ReviewModel review = reviews.get(position);

        holder.orderId.setText("Mã đơn hàng : " + review.getOrderId());
        holder.orderDate.setText(review.getOrderDate());
        holder.productName.setText(review.getProductName());
        holder.customerName.setText("Tên: " + review.getCustomerName());
        holder.reviewText.setText(review.getReviewText());
        holder.ratingBar.setRating(review.getRating());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {

        TextView orderId, orderDate, productName, customerName, reviewText;
        RatingBar ratingBar;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.orderId);
            orderDate = itemView.findViewById(R.id.orderDate);
            productName = itemView.findViewById(R.id.productName);
            customerName = itemView.findViewById(R.id.customerName);
            reviewText = itemView.findViewById(R.id.reviewText);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
