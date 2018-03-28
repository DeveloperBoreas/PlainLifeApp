package com.boreas.model.entity;

import com.boreas.interactor.Pic;

import java.util.List;

/**
 * 作者 boreas
 * 日期 18-3-28
 * 邮箱 13051089921@163.com
 */

public class PicEntity {
    private List<Pic> picList;

    public List<Pic> getPicList() {
        return picList;
    }

    public void setPicList(List<Pic> picList) {
        this.picList = picList;
    }

    public static class Pic{
        private String name;
        private byte[] date;
        private String desc;

        public Pic(String name, byte[] date, String desc) {
            this.name = name;
            this.date = date;
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public byte[] getDate() {
            return date;
        }

        public void setDate(byte[] date) {
            this.date = date;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
