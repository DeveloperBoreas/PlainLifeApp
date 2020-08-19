package com.boreas.plainlife.mvp;

/**
 * 视图接口基类
 */
public interface ViewInterface {
    /**
     * 没有网络状态回调
     */
    void noNetWork();

    /**
     * 无数据状态回调
     */
    void noData();

    /**
     * 消除LoadingDialog
     */
    void onDisLoadingDialog();

    /**
     * 显示LoadingDialog
     */
    void onShowLoadingDialog();
}
