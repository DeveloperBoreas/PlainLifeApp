package com.boreas.plainlife.mvp.models.login;

import com.boreas.plainlife.base.BaseResponse;

public class LoginReceModel extends BaseResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginReceModel{" +
                "token='" + token + '\'' +
                '}';
    }
}
