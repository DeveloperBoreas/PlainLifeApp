package com.boreas.plainlife.mvp.models.shopping;

import com.boreas.plainlife.mvp.ModelInterface;

import java.util.ArrayList;

public class ShoppingCartModel implements ModelInterface {

    private ArrayList<ShoppingData> data;
    private String msg;
    private int status;

    public ArrayList<ShoppingData> getData() {
        return data;
    }

    public void setData(ArrayList<ShoppingData> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ShoppingData {
        private String id;
        private String name;
        private String type;
        private String img;
        private String type_en;
        private String location;
        private int status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getType_en() {
            return type_en;
        }

        public void setType_en(String type_en) {
            this.type_en = type_en;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "ShoppingData{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", img='" + img + '\'' +
                    ", type_en='" + type_en + '\'' +
                    ", location='" + location + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ShoppingCartModel{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                ", status=" + status +
                '}';
    }
}
