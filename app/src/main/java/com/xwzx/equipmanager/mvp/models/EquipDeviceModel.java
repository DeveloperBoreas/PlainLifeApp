package com.xwzx.equipmanager.mvp.models;

import com.xwzx.equipmanager.mvp.ModelInterface;

public class EquipDeviceModel implements ModelInterface {
    private int id;
    private String deviceTitle;
    private String goodsLocation;
    private String iconUrl;

    public EquipDeviceModel(int id, String deviceTitle, String goodsLocation) {
        this.id = id;
        this.deviceTitle = deviceTitle;
        this.goodsLocation = goodsLocation;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceTitle() {
        return deviceTitle;
    }

    public void setDeviceTitle(String deviceTitle) {
        this.deviceTitle = deviceTitle;
    }

    public String getGoodsLocation() {
        return goodsLocation;
    }

    public void setGoodsLocation(String goodsLocation) {
        this.goodsLocation = goodsLocation;
    }
}
