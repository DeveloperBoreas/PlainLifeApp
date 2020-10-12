package com.boreas.plainlife.mvp.presenters.ipresenter;

import com.boreas.plainlife.mvp.Presenter;

public interface ILocationMapActivityPresenter extends Presenter {
    void queryUserLocation(Long uid);
}
