package com.boreas.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

/**
 * 作者 boreas
 * 日期 18-3-14
 * 邮箱 13051089921@163.com
 * @author boreas
 */

public abstract class BaseFragment extends Fragment {

    private static final String TAG = UUID.randomUUID().toString();
    /** 标识fragment视图已经初始化完毕**/
    private boolean isViewPrepared;
    /** 标识已经触发过懒加载数据 **/
    private boolean hasFetchData;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater,container,savedInstanceState);
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

    /**
     *  初始化布局
     * @return RootView
     */
    public abstract View initView(LayoutInflater inflater,ViewGroup container,Bundle bundle);

    /**
     *  延迟获取数据
     */
    public abstract void lazyFetchData();
}
