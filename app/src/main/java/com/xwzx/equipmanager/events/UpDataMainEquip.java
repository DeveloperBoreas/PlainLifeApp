package com.xwzx.equipmanager.events;

public class UpDataMainEquip {
    private boolean upData;

    public UpDataMainEquip(boolean upData) {
        this.upData = upData;
    }

    public boolean isUpData() {
        return upData;
    }

    public void setUpData(boolean upData) {
        this.upData = upData;
    }
}
