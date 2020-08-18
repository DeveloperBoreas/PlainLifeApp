package com.xwzx.equipmanager.mvp.models;

import com.xwzx.equipmanager.mvp.ModelInterface;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;

@Entity
public class LoginModel implements ModelInterface {
    @Id
    public long id;

    @Keep
    public LoginModel(long id) {
        this.id = id;
    }

    @Keep
    public LoginModel() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
