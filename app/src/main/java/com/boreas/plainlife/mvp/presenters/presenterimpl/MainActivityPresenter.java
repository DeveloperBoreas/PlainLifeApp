package com.boreas.plainlife.mvp.presenters.presenterimpl;

import android.content.Context;

import com.boreas.plainlife.App;
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
    private Disposable testSubscribe;
    private RabbitMQConfiguration rabbitMQConfiguration;

    @Inject
    public MainActivityPresenter(ApiService apiService, MainActivityInterface mainActivityInterface, Context context, App app, WebSocketManger webSocketManger, RabbitMQConfiguration rabbitMQConfiguration) {
        this.apiService = apiService;
        this.mainActivityInterface = mainActivityInterface;
        this.context = context;
        this.app = app;
        this.webSocketManger = webSocketManger;
        this.rabbitMQConfiguration = rabbitMQConfiguration;
    }

    private void handlerReceiverPositionMessage() {
//        RxTimer hbRxTimer = new RxTimer();
//        hbRxTimer.interval(5000, number -> {
//            this.rabbitMQConfiguration.basicPublish(() -> rabbitMQConfiguration.hbService());
//        });
//        //处理接收内容
//        rabbitMQConfiguration.basicConsumer(jsonString -> {
////                System.out.println("handlerReceiverPositionMessage : " + jsonString);
//        });
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
        this.handlerReceiverPositionMessage();
        this.sendMessage();
    }

    public void sendMessage() {
        RxTimer hbRxTimer = new RxTimer();
        hbRxTimer.interval(5000, number -> {
            if (this.webSocketManger.getWebSocket() != null) {
                this.webSocketManger.sendMessage("测试内容");
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
        if (testSubscribe != null && !testSubscribe.isDisposed()) {
            testSubscribe.dispose();
        }
    }
}
