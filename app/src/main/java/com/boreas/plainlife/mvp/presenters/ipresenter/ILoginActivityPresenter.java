package com.boreas.plainlife.mvp.presenters.ipresenter;

import com.boreas.plainlife.mvp.Presenter;

public interface ILoginActivityPresenter extends Presenter {
    void requestData(String userName,String password);
}
