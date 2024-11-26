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

import com.hcm.sale_laptop.data.api.ApiService;
import com.hcm.sale_laptop.data.api.RetrofitClient;
import com.hcm.sale_laptop.data.model.other.ReviewModel;
import com.hcm.sale_laptop.ui.adapter.ReviewAdapter;

import com.hcm.sale_laptop.R;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminReviewListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private List<ReviewModel> reviewList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_list, container, false);

        recyclerView = view.findViewById(R.id.reviewRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        reviewList = new ArrayList<>();
        adapter = new ReviewAdapter(reviewList);
        recyclerView.setAdapter(adapter);

        fetchReviews();

        return view;
    }
    private void fetchReviews() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.BASE_URL + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<ReviewModel>> call = apiService.getReview();

        call.enqueue(new Callback<List<ReviewModel>>() {
            @Override
            public void onResponse(Call<List<ReviewModel>> call, retrofit2.Response<List<ReviewModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ReviewModel> orders = response.body();
                    adapter = new ReviewAdapter(orders);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Không tải được dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ReviewModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi API: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}