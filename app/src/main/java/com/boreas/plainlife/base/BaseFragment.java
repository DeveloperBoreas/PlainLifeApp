package com.boreas.plainlife.base;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.boreas.plainlife.R;
import com.boreas.plainlife.widget.loading.LoadingView;


/**
 * author : 王秀清
 * e-mail :  13051089921@163.com
 * <p>
 * des    :  懒加载
 */
public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();
    /**
     * 标识fragment视图已经初始化完毕
     **/
    private boolean isViewPrepared;
    /**
     * 标识已经触发过懒加载数据
     **/
    private boolean hasFetchData;

    public T binding;
    private View view;

    public LoadingView mDialog;
    private String name;

    public BaseFragment() {
    }

    public BaseFragment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, setContent(), container, false);
        initComponent();
        initView();
        initListener();
        view = binding.getRoot();
        return view;
    }

    protected <T extends View> T getView(int id) {
        return (T) view.findViewById(id);
    }

    private void lazyFetchDataIfPrepared() {
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true;
            lazyFetchData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyFetchDataIfPrepared();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
        lazyFetchDataIfPrepared();
    }

    @Override
    public void onDestroyView() {
        super.onDestroy();
        super.onDestroyView();
        hasFetchData = false;
        isViewPrepared = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void showLoadingDialog() {
        if (mDialog == null) {
            mDialog = new LoadingView(getActivity(), R.style.CustomDialog);
            mDialog.show();
        }
    }

    public void dismissLoadingDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        mDialog = null;
    }

    /**
     * 延迟获取数据
     */
    public abstract void lazyFetchData();

    public abstract int setContent();

    public abstract void initView();

    public abstract void initListener();

    public abstract void initComponent();

    public abstract boolean onBackPressed();
}
