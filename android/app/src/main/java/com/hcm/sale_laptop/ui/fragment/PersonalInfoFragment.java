package com.hcm.sale_laptop.ui.fragment;

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
import com.hcm.sale_laptop.data.model.other.PasswordRequestModel;
import com.hcm.sale_laptop.data.model.other.UserModel;
import com.hcm.sale_laptop.databinding.FragmentPersonalInformationBinding;
import com.hcm.sale_laptop.ui.viewmodel.MainActivityViewModel;
import com.hcm.sale_laptop.utils.Constants;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonalInfoFragment extends BaseFragment<BaseViewModel<?>, FragmentPersonalInformationBinding> {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentPersonalInformationBinding.inflate(inflater, container, false);
        setup();
        return mBinding.getRoot();
    }

    @Override
    protected void setupUI() {
        hideOrShowBottomNavi(false);
        this.setupDataUser();
    }

    private void setupDataUser() {
        UserModel userModel =  Constants.getUserModel();
        mBinding.edtAccountName.setText(userModel.getName());
        mBinding.edtPhoneNumber.setText(userModel.getPhoneNumber());
        mBinding.edtGender.setText(userModel.getBio());
        mBinding.edtDateOfBirth.setText(String.valueOf(userModel.getAge()));
    }

    @Override
    protected void setupAction() {

        setOnClickListener(mBinding.btnBackArrow, view -> onBack());
        setOnClickListener(mBinding.btnPay, view -> {
            UserModel userModel =  Constants.getUserModel();
            userModel.setName(mBinding.edtAccountName.getText().toString());
            userModel.setPhoneNumber(mBinding.edtPhoneNumber.getText().toString());
            userModel.setBio(mBinding.edtGender.getText().toString());
            int foo;
            try {
                foo = Integer.parseInt(mBinding.edtDateOfBirth.getText().toString());
            }
            catch (NumberFormatException e) {
                foo = 0;
            }
            userModel.setAge(foo);
            this.sendProfile(userModel);
        });
    }

    private void sendProfile(UserModel userModel) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Void> call = apiService.updateProfile(userModel);
        call.enqueue(new retrofit2.Callback<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Thay đổi thông tin thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Thay đổi thông tin thất bại", Toast.LENGTH_SHORT).show();
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
