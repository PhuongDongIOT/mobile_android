package com.hcm.sale_laptop.ui.fragment.manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import com.hcm.sale_laptop.R;
import com.hcm.base.BaseFragment;
import com.hcm.base.BaseViewModel;
import com.hcm.sale_laptop.data.model.other.ProductModel;
import com.hcm.sale_laptop.databinding.FragmentChangePasswordBinding;
import com.hcm.sale_laptop.ui.fragment.ShoppingCartFragment;
import com.hcm.sale_laptop.ui.viewmodel.MainActivityViewModel;
import com.hcm.sale_laptop.utils.AppUtils;
import com.hcm.sale_laptop.utils.CartManager;

import java.util.List;

public class AdminChangePasswordFragment extends BaseFragment<BaseViewModel<?>, FragmentChangePasswordBinding> {
    private EditText passwordEditText;
    private EditText resetPasswordEditText;
    private Button submitButton;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentChangePasswordBinding.inflate(inflater, container, false);
        setup();
        return mBinding.getRoot();
    }

    @Override
    protected void setupUI() {
//        hideOrShowBottomNavi(false);
    }

    @Override
    protected void setupAction() {

//        setOnClickListener(mBinding.btnBackArrow, view -> onBack());
        setOnClickListener(mBinding.btnPay, view -> {
            Toast.makeText(getActivity(), "Please enter a password", Toast.LENGTH_SHORT).show();
//            showDialogWarning();
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
        hideOrShowBottomNavi(true);
    }

    private void hideOrShowBottomNavi(boolean isShow) {
        final MainActivityViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        mainViewModel.setBottomNavVisibility(isShow);
    }
}
