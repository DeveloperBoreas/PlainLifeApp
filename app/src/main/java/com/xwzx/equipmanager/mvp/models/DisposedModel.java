package com.xwzx.equipmanager.mvp.models;

import com.xwzx.equipmanager.mvp.ModelInterface;

import java.util.ArrayList;

public class DisposedModel implements ModelInterface {
    private int id;
    private String imgUrl;
    private String title;
    private String porposer;
    private int statu;
    private ArrayList<DisposedChildModel> disposedDeivceList;

    public static class DisposedChildModel implements ModelInterface{
        private int id;
        private String url;
        private String title;
        private String shoppinglocation;
        private int statu;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShoppinglocation() {
            return shoppinglocation;
        }

        public void setShoppinglocation(String shoppinglocation) {
            this.shoppinglocation = shoppinglocation;
        }

        public int getStatu() {
            return statu;
        }

        public void setStatu(int statu) {
            this.statu = statu;
        }

        public DisposedChildModel(int id, String url, String title, String shoppinglocation, int statu) {
            this.id = id;
            this.url = url;
            this.title = title;
            this.shoppinglocation = shoppinglocation;
            this.statu = statu;
        }

        @Override
        public String toString() {
            return "DisposedChildModel{" +
                    "id=" + id +
                    ", url='" + url + '\'' +
                    ", title='" + title + '\'' +
                    ", shoppinglocation='" + shoppinglocation + '\'' +
                    ", statu=" + statu +
                    '}';
        }
    }

    public DisposedModel(int id, String imgUrl, String title, String porposer, int statu, ArrayList<DisposedChildModel> disposedDeivceList) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.title = title;
        this.porposer = porposer;
        this.statu = statu;
        this.disposedDeivceList = disposedDeivceList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPorposer() {
        return porposer;
    }

    public void setPorposer(String porposer) {
        this.porposer = porposer;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

    public ArrayList<DisposedChildModel> getDisposedDeivceList() {
        return disposedDeivceList;
    }

    public void setDisposedDeivceList(ArrayList<DisposedChildModel> disposedDeivceList) {
        this.disposedDeivceList = disposedDeivceList;
    }

    @Override
    public String toString() {
        return "DisposedModel{" +
                "id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                ", title='" + title + '\'' +
                ", porposer='" + porposer + '\'' +
                ", statu=" + statu +
                ", disposedDeivceList=" + disposedDeivceList +
                '}';
    }
}
