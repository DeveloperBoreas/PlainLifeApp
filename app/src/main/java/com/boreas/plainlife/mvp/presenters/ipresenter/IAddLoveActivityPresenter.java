package com.boreas.plainlife.mvp.presenters.ipresenter;

import com.boreas.plainlife.mvp.Presenter;

public interface IAddLoveActivityPresenter extends Presenter {
    void bindUserByPhone(String phone);
    void queryUserByPhone(String phone);
}
