package com.boreas.plainlife.mvp.models.location;

import com.boreas.plainlife.base.BaseResponse;

public class LocationUserModel extends BaseResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data{
        private Long userId;
        private String userName = "";
        private String nickName = "";
        private String email = "";
        private String avatar = "";
        private String phonenumber = "";
        private String sex = "";

        public Data() {
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "UserVo{" +
                    "userId=" + userId +
                    ", userName='" + userName + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", email='" + email + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", phonenumber='" + phonenumber + '\'' +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }
}
