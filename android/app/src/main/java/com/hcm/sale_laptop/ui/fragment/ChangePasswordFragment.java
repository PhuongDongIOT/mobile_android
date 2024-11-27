package com.hcm.sale_laptop.ui.fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.hcm.base.BaseFragment;
import com.hcm.base.BaseViewModel;
import com.hcm.sale_laptop.data.api.ApiService;
import com.hcm.sale_laptop.data.api.RetrofitClient;
import com.hcm.sale_laptop.data.model.other.OrderStateModel;
import com.hcm.sale_laptop.data.model.other.PasswordRequestModel;
import com.hcm.sale_laptop.data.model.other.UserModel;
import com.hcm.sale_laptop.data.repository.PasswordRequestRepository;
import com.hcm.sale_laptop.databinding.FragmentChangePasswordBinding;
import com.hcm.sale_laptop.ui.adapter.RequestCancelOrderAdapter;
import com.hcm.sale_laptop.ui.viewmodel.MainActivityViewModel;
import com.hcm.sale_laptop.utils.Constants;

import java.util.List;

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
            String password = mBinding.edtOldPass.getText().toString().trim();
            String newPassword = mBinding.edtNewPass.getText().toString().trim();
            String reNewPassword = mBinding.edtReEnterNewPass.getText().toString().trim();
            if (password.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter a password", Toast.LENGTH_SHORT).show();
            } else if (!newPassword.equals(reNewPassword)) {
                Toast.makeText(getActivity(), "Mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
            }
            else {
                // Gửi mật khẩu lên server
                sendPassword(password, newPassword, reNewPassword);
            }
        });
    }
    private void sendPassword(String password, String newPassword, String reNewPassword) {

        UserModel user = Constants.getUserModel();
        String email = user.getEmail();
        PasswordRequestModel passwordRequest = new PasswordRequestModel(email, password, newPassword, reNewPassword);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Void> call = apiService.sendPassword(passwordRequest);
        call.enqueue(new retrofit2.Callback<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    onBack();
                } else {
                    Toast.makeText(getActivity(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                }
            }


            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
