package com.hcm.sale_laptop.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.hcm.base.BaseFragment;
import com.hcm.sale_laptop.data.model.other.ProductModel;
import com.hcm.sale_laptop.databinding.FragmentSubmitReviewBinding;
import com.hcm.sale_laptop.ui.viewmodel.OrderReviewViewModel;
import com.hcm.sale_laptop.utils.AppUtils;
import com.hcm.sale_laptop.utils.Constants;

public class SubmitReviewFragment extends BaseFragment<OrderReviewViewModel, FragmentSubmitReviewBinding> {
    private ProductModel productModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentSubmitReviewBinding.inflate(inflater, container, false);
        final Bundle bundle = getArguments();
        if (bundle == null) return mBinding.getRoot();

        final ProductModel productModel = bundle.getParcelable(Constants.KEY_PRODUCT_MODEL);
        if (productModel == null) return mBinding.getRoot();

        this.productModel = productModel;

        setup();

        return mBinding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void setupUI() {
        AppUtils.loadImageUrl(mBinding.imageView, productModel.getPicture());
        mBinding.tvNameProduct.setText(productModel.getTitle());
        mBinding.tvPrice.setText(AppUtils.customPrice(productModel.getPrice()));
        mBinding.tvQuantity.setText("" + productModel.getQuantity());
    }

    @Override
    protected void setupAction() {

        mViewModel = new OrderReviewViewModel();


        mViewModel.errorMessage.observe(this, this::showToast);

        mViewModel.isLoading.observe(this, isLoading -> {
            if (isLoading) {
                showProgressBar();
            } else {
                hideProgressBar();
            }
        });

    }

    @Override
    protected void setupData() {

    }

    @Override
    protected int getLayoutResourceId() {
        return mBinding.getRoot().getId();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
