package com.hcm.sale_laptop.ui.fragment;

import retrofit2.Callback;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.hcm.base.BaseFragment;
import com.hcm.base.BaseViewModel;
import com.hcm.sale_laptop.data.repository.PasswordRequestRepository;
import com.hcm.sale_laptop.databinding.FragmentChangePasswordBinding;
import com.hcm.sale_laptop.ui.viewmodel.MainActivityViewModel;

public class ChangePasswordFragment extends BaseFragment<BaseViewModel<?>, FragmentChangePasswordBinding> {
    private PasswordRequestRepository passwordRequestRepository;
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
        hideOrShowBottomNavi(false);

    }

    @Override
    protected void setupAction() {

        setOnClickListener(mBinding.btnBackArrow, view -> onBack());
        setOnClickListener(mBinding.btnPay, view -> {
            String password = mBinding.edtAccountName.getText().toString().trim();
            if (password.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter a password", Toast.LENGTH_SHORT).show();
            } else {
                // Gửi mật khẩu lên server
                sendPassword(password);
            }
        });
    }
    private void sendPassword(String password) {
        passwordRequestRepository.sendPassword(password, new PasswordRequestRepository.ApiCallback() {
            @Override
            public void onSuccess(String message) {
                // Hiển thị kết quả thành công
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                // Hiển thị lỗi
                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
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
        hideOrShowBottomNavi(true);
    }

    private void hideOrShowBottomNavi(boolean isShow) {
        final MainActivityViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        mainViewModel.setBottomNavVisibility(isShow);
    }
}
