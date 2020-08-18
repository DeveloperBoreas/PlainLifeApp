package com.xwzx.equipmanager.mvp.presenters.ipresenter;

import com.xwzx.equipmanager.mvp.Presenter;

public interface ILoginActivityPresenter extends Presenter {
    void requestData(String userName,String password);
}
