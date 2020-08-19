package com.boreas.plainlife.base;

import android.content.Context;

import com.boreas.plainlife.App;
import com.boreas.plainlife.db.LoginModelDao;
import com.boreas.plainlife.utils.NetWorkUtil;


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
