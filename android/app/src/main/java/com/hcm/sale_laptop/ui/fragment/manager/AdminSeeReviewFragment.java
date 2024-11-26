package com.hcm.sale_laptop.ui.fragment.manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.hcm.base.BaseFragment;
import com.hcm.base.BaseViewModel;
import com.hcm.sale_laptop.data.model.other.ProductModel;
import com.hcm.sale_laptop.databinding.FragmentAdminSeeReviewBinding;
import com.hcm.sale_laptop.ui.adapter.AdminRateAdapter;

import java.util.ArrayList;

public class AdminSeeReviewFragment extends BaseFragment<BaseViewModel<?>, FragmentAdminSeeReviewBinding> {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentAdminSeeReviewBinding.inflate(inflater, container, false);
        setup();
        return mBinding.getRoot();
    }

    @Override
    protected void setupUI() {

        final ArrayList<ProductModel> arrayList = new ArrayList<>();
        final ProductModel model = new ProductModel("id", "category_id", "title", "slug", "picture", "summary", "description", 100, "created_by", 54, 54);
        arrayList.add(model);
        arrayList.add(model);

        final AdminRateAdapter adapter = new AdminRateAdapter(arrayList, this::onClickDiscountedProduct);
        mBinding.recyclerView.setAdapter(adapter);
        adapter.setItems(arrayList);
    }

    @Override
    protected void setupAction() {

    }

    private void onClickDiscountedProduct(ProductModel object) {

    }

    @Override
    protected void setupData() {

    }
}