package com.xwzx.equipmanager.base;

import android.content.Context;

import com.xwzx.equipmanager.App;
import com.xwzx.equipmanager.db.LoginModelDao;
import com.xwzx.equipmanager.utils.NetWorkUtil;


public abstract class BaseRequest {
    private Context context;
    public LoginModelDao loginModelDao;

    public BaseRequest() {
        this.context = App.getInstance().getApplicationContext();
        this.loginModelDao = App.getInstance().getmDBComponent().getLoginModelDao();
    }

    /**
     * 清空数据
     */
    public void clearLoginModel() {

    }

    public boolean isNetWorkEnable() {
        if (context == null) {
            throw new NullPointerException("---------BaseRequest  context is not NULL !!!!!!!!!!!!!");
        }
        return NetWorkUtil.isNetWorkEnable(context);
    }
}
