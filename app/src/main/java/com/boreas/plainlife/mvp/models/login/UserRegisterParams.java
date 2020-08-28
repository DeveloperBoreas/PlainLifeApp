package com.boreas.plainlife.mvp.models.login;

public class UserRegisterParams {
    private String userName;
    private String password;
    private String phonenumber;
    private String verCode;

    public UserRegisterParams(String userName, String password, String phonenumber, String verCode) {
        this.userName = userName;
        this.password = password;
        this.phonenumber = phonenumber;
        this.verCode = verCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    @Override
    public String toString() {
        return "UserRegisterParams{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", verCode='" + verCode + '\'' +
                '}';
    }
}
