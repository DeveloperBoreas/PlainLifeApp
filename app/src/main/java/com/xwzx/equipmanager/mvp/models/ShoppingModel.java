package com.xwzx.equipmanager.mvp.models;

import com.xwzx.equipmanager.mvp.ModelInterface;

import java.util.ArrayList;

public class ShoppingModel implements ModelInterface {
    private int id;
    private boolean checked;
    private String title;
    private ArrayList<ShoppingChildModel> childModels;

    public ShoppingModel() {

    }

    public ShoppingModel(int id, boolean checked, String title, ArrayList<ShoppingChildModel> childModels) {
        this.id = id;
        this.checked = checked;
        this.title = title;
        this.childModels = childModels;
    }

    public int getId() {
        return id;
    }

    public ShoppingModel setId(int id) {
        this.id = id;
        return this;
    }

    public boolean isChecked() {
        return checked;
    }

    public ShoppingModel setChecked(boolean checked) {
        this.checked = checked;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ShoppingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public ArrayList<ShoppingChildModel> getChildModels() {
        return childModels;
    }

    public ShoppingModel setChildModels(ArrayList<ShoppingChildModel> childModels) {
        this.childModels = childModels;
        return this;
    }

    public static class ShoppingChildModel implements ModelInterface {
        private String id;
        private boolean checked;
        private String title;
        private String goodsLocation;
        private String imgUrl;
        private String type;
        private String type_en;
        private int status;

        public ShoppingChildModel (){

        }

        public ShoppingChildModel(String id, boolean checked, String title, String goodsLocation, String imgUrl, String type, String type_en, int status) {
            this.id = id;
            this.checked = checked;
            this.title = title;
            this.goodsLocation = goodsLocation;
            this.imgUrl = imgUrl;
            this.type = type;
            this.type_en = type_en;
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public ShoppingChildModel setId(String id) {
            this.id = id;
            return this;
        }

        public boolean isChecked() {
            return checked;
        }

        public ShoppingChildModel setChecked(boolean checked) {
            this.checked = checked;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public ShoppingChildModel setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getGoodsLocation() {
            return goodsLocation;
        }

        public ShoppingChildModel setGoodsLocation(String goodsLocation) {
            this.goodsLocation = goodsLocation;
            return this;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public ShoppingChildModel setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        public String getType() {
            return type;
        }

        public ShoppingChildModel setType(String type) {
            this.type = type;
            return this;
        }

        public String getType_en() {
            return type_en;
        }

        public ShoppingChildModel setType_en(String type_en) {
            this.type_en = type_en;
            return this;
        }

        public int getStatus() {
            return status;
        }

        public ShoppingChildModel setStatus(int status) {
            this.status = status;
            return this;
        }
    }

}
