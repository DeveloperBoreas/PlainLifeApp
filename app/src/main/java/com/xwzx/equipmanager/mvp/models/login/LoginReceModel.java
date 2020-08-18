package com.xwzx.equipmanager.mvp.models.login;

import android.os.Parcel;
import android.os.Parcelable;

import com.xwzx.equipmanager.base.BaseResponse;

import java.util.List;

public class LoginReceModel extends BaseResponse implements Parcelable {
    private String code;
    private String msg;
    private String username;
    private String token_type;
    private String access_token;
    private int expires_in;
    private String refresh_token;
    private List<String> roles;

    public LoginReceModel(String code, String msg, String username, String token_type, String access_token, int expires_in, String refresh_token, List<String> roles) {
        this.code = code;
        this.msg = msg;
        this.username = username;
        this.token_type = token_type;
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.refresh_token = refresh_token;
        this.roles = roles;
    }

    public LoginReceModel() {
    }

    protected LoginReceModel(Parcel in) {
        code = in.readString();
        msg = in.readString();
        username = in.readString();
        token_type = in.readString();
        access_token = in.readString();
        expires_in = in.readInt();
        refresh_token = in.readString();
        roles = in.createStringArrayList();
    }

    public static final Creator<LoginReceModel> CREATOR = new Creator<LoginReceModel>() {
        @Override
        public LoginReceModel createFromParcel(Parcel in) {
            return new LoginReceModel(in);
        }

        @Override
        public LoginReceModel[] newArray(int size) {
            return new LoginReceModel[size];
        }
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(code);
        parcel.writeString(msg);
        parcel.writeString(username);
        parcel.writeString(token_type);
        parcel.writeString(access_token);
        parcel.writeInt(expires_in);
        parcel.writeString(refresh_token);
        parcel.writeStringList(roles);
    }

    @Override
    public String toString() {
        return "LoginReceModel{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", username='" + username + '\'' +
                ", token_type='" + token_type + '\'' +
                ", access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", roles=" + roles +
                '}';
    }
}
