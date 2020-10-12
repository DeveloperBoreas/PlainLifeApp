package com.boreas.plainlife;

import com.boreas.plainlife.mvp.models.main.UserInfo;

public class ObjectPool {
    private volatile static ObjectPool mInstance;

    private ObjectPool() {
    }

    public static ObjectPool getInstance() {
        if (mInstance == null) {
            synchronized (ObjectPool.class) {
                if (mInstance == null) {
                    mInstance = new ObjectPool();
                }
            }
        }
        return mInstance;
    }
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
