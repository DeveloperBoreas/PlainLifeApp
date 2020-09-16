package com.boreas.plainlife.mvp.presenters.presenterimpl;

import android.content.Context;

import com.boreas.plainlife.App;
import com.boreas.plainlife.websocket.PlainMessage;
import com.boreas.plainlife.websocket.WebSocketManger;
import com.boreas.plainlife.api.ApiService;
import com.boreas.plainlife.base.BaseRequest;
import com.boreas.plainlife.mq.RabbitMQConfiguration;
import com.boreas.plainlife.mvp.presenters.ipresenter.IMainActivityPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.MainActivityInterface;
import com.boreas.plainlife.utils.RxTimer;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class MainActivityPresenter extends BaseRequest implements IMainActivityPresenter {
    private ApiService apiService;
    private MainActivityInterface mainActivityInterface;
    private Context context;
    private App app;
    private WebSocketManger webSocketManger;
    private PlainMessage plainMessage;
    private Disposable subscribe;

    @Inject
    public MainActivityPresenter(ApiService apiService, MainActivityInterface mainActivityInterface, Context context, App app, WebSocketManger webSocketManger, PlainMessage plainMessage) {
        this.apiService = apiService;
        this.mainActivityInterface = mainActivityInterface;
        this.context = context;
        this.app = app;
        this.webSocketManger = webSocketManger;
        this.plainMessage = plainMessage;
    }


    @Override
    public void requestData() {
        if (isNetWorkEnable()) {

        } else {
            mainActivityInterface.noNetWork();
        }
    }

    @Override
    public void onInit() {
        this.webSocketManger.init();
        this.sendHBMessage();
    }

    public void sendHBMessage() {
        RxTimer hbRxTimer = new RxTimer();
        hbRxTimer.interval(5000, number -> {
            if (this.webSocketManger.getWebSocket() != null) {
                this.webSocketManger.sendMessage(this.plainMessage.hbService());
            }
        });
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestory() {
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
    }
}
