package com.boreas.plainlife.mvp.models.login;

import com.boreas.plainlife.base.BaseResponse;

public class CaptchatModel extends BaseResponse {
    private String uuid;
    private String img;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "CaptchatModel{" +
                "uuid='" + uuid + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
