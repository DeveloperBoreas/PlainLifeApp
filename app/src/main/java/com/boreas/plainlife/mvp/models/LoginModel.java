package com.boreas.plainlife.mvp.models;

import com.boreas.plainlife.mvp.ModelInterface;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Generated;

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