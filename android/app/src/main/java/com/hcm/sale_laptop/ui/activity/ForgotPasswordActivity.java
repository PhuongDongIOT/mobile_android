package com.hcm.sale_laptop.ui.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.hcm.base.BaseActivity;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.data.api.ApiService;
import com.hcm.sale_laptop.data.api.RetrofitClient;
import com.hcm.sale_laptop.data.model.other.OrderListPostModel;
import com.hcm.sale_laptop.data.model.other.ResetPasswordModel;
import com.hcm.sale_laptop.databinding.ActivityForgotPasswordBinding;
import com.hcm.sale_laptop.ui.viewmodel.ForgotPasswordActivityViewModel;
import com.hcm.sale_laptop.ui.viewmodel.factory.ForgotPasswordActivityViewModelFactory;
import com.hcm.sale_laptop.utils.AppUtils;
import com.hcm.sale_laptop.utils.ConstantsList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgotPasswordActivity extends BaseActivity<ForgotPasswordActivityViewModel, ActivityForgotPasswordBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        setup();
    }

    @Override
    protected void setupUI() {
        final String registerOrLogin = getString(R.string.register_or_login);
        final String register = getString(R.string.register);
        final String login = getString(R.string.login);
        final SpannableString spannableString = new SpannableString(registerOrLogin);
        setUpSpannableString(spannableString, registerOrLogin, register, R.color.blue_sea, RegisterActivity.class);
        setUpSpannableString(spannableString, registerOrLogin, login, R.color.blue_sky, LoginActivity.class);
        mBinding.txtRegisterOrLogin.setText(spannableString);
        mBinding.txtRegisterOrLogin.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected void setupAction() {
        setOnClickListener(mBinding.btnLogin, view -> {
            final String email = mBinding.editUserName.getText().toString();
            if (email.isEmpty()) {
                showToast("Bạn chưa nhập email");
                return;
            }

            if (!AppUtils.isEmailValid(email)) {
                showToast("Định dạng bạn nhập chưa phải là định dạng của email");
                return;
            }

            this.postCancelOder(email);
        });
    }

    private void postCancelOder (String email) {
        ResetPasswordModel resetPasswordModel = new ResetPasswordModel(email);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Void> call = apiService.resetPassword(resetPasswordModel);
        call.enqueue(new retrofit2.Callback<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    showToast("Succeed to fetch reset password");
                    navigateTo(LoginActivity.class);
                } else {
                    showToast("Failed to fetch reset password");
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Error: " + t.getMessage());
            }
        });
    }

    @Override
    protected void setupData() {
        mViewModel = new ViewModelProvider(this, new ForgotPasswordActivityViewModelFactory(getApplication()))
                .get(ForgotPasswordActivityViewModel.class);
    }

}