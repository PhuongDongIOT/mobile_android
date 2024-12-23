package com.hcm.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment<V extends BaseViewModel<?>, B extends ViewBinding> extends Fragment {

    protected V mViewModel;
    protected B mBinding;
    private Context mContext;
    private BaseActivity<V, B> mActivity;

    protected abstract void setupUI();

    protected abstract void setupAction();

    protected abstract void setupData();

    protected void setup() {
        setupUI();
        setupData();
        setupAction();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;  // Gán context của Fragment
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity<V, B>) context;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResourceId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Đây là nơi thực hiện các thao tác sau khi view đã được tạo
    }

    /**
     * Hiển thị một thông báo Toast ngắn.
     *
     * @param message Chuỗi thông báo cần hiển thị.
     */
    public void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Thêm Fragment vào container trong Activity.
     *
     * @param fragment       Fragment mới cần thêm.
     * @param containerId    ID của container trong layout.
     * @param addToBackStack Có thêm vào back stack hay không.
     */
    @SuppressLint("CommitTransaction")
    public void addFragment(Fragment fragment, int containerId, boolean addToBackStack) {
        final FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    /**
     * Thay thế Fragment trong container.
     *
     * @param fragment       Fragment mới cần thay thế.
     * @param containerId    ID của container trong layout.
     * @param addToBackStack Có thêm vào back stack hay không.
     */
    public void replaceFragment(Fragment fragment, int containerId, boolean addToBackStack) {
        final FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }

    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public BaseActivity<V, B> getBaseActivity() {
        return mActivity;
    }

    public void setOnTouchListener(View view, View.OnTouchListener listener) {
        view.setOnTouchListener(listener);
    }

    public void setOnClickListener(View view, View.OnClickListener listener) {
        view.setOnClickListener(listener);
    }

    /**
     * Xóa Fragment hiện tại.
     */
    public void removeFragment() {
        final FragmentManager fragmentManager = getParentFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(this);
        transaction.commit();
    }

    /**
     * Hàm này có thể được override ở các Fragment con để thiết lập layout cụ thể.
     *
     * @return ID của layout fragment.
     */
    protected int getLayoutResourceId() {
        if (mBinding != null) {
            return mBinding.getRoot().getId();
        }
        return 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Xử lý dọn dẹp tài nguyên khi view bị phá hủy
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;  // Xóa bỏ tham chiếu đến context
    }

    protected void showProgressBar() {
        if (mActivity != null) {
            mActivity.showProgressBar();
        }
    }

    protected BaseFragment<?, ?> getBaseFragment() {
        return this;
    }

    protected void hideProgressBar() {
        if (mActivity != null) {
            mActivity.hideProgressBar();
        }
    }

    protected void onBack() {
        getParentFragmentManager().popBackStack();
    }

    public interface Callback {
        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}

