package com.boreas.plainlife.websocket;


import android.util.Log;

import com.boreas.plainlife.Constant;
import com.boreas.plainlife.ObjectPool;
import com.boreas.plainlife.utils.PreUtil;
import com.boreas.plainlife.utils.RxTimer;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

public class WebSocketManger {

    private static final String TAG = WebSocketManger.class.getSimpleName();
    private volatile static WebSocketManger mInstance;
    private PlainWebSocketClient webSocket;
    private ArrayList<WebSocketListener> socketListeners = new ArrayList<>();
    private static final int HEART_BEAT_RATE = 10 * 1000;
    private RxTimer testConnectionRxTimer;

    @Inject
    ObjectPool objectPool;

    private WebSocketManger() {
    }

    public static WebSocketManger getInstance() {
        if (mInstance == null) {
            synchronized (WebSocketManger.class) {
                if (mInstance == null) {
                    mInstance = new WebSocketManger();
                }
            }
        }
        return mInstance;
    }

    public void init() {
        this.webSocket = new PlainWebSocketClient(URI.create(Constant.WEBSOCKET_URL), new HashMap<String, String>() {{
            put("Authorization", (String) PreUtil.get(Constant.TOKEN_KEY, ""));
            put("uid", String.valueOf(objectPool.getUserInfo().getUser().getUserId()));
        }}) {
            @Override
            public void onMessage(String message) {
                if (!socketListeners.isEmpty()) {
                    for (WebSocketListener webSocketListener : socketListeners) {
                        webSocketListener.onMessage(message);
                    }
                }
            }
        };
        try {
            this.webSocket.connectBlocking();
            this.testConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void registerSocketListener(WebSocketListener webSocketListener) {
        if (this.socketListeners.contains(webSocketListener)) {
            throw new IllegalArgumentException("不能重复注册Socket");
        }
        this.socketListeners.add(webSocketListener);
    }

    public void unRegisterSocketListener(WebSocketListener webSocketListener) {
        if (this.socketListeners.contains(webSocketListener)) {
            this.socketListeners.remove(webSocketListener);
        }
    }

    public void sendMessage(String message) {
//        Log.e(TAG, " 发送的内容:" + message);
        if (this.webSocket == null || !this.webSocket.isOpen()) {
            Log.e(TAG, " 连接正在建立中");
            return;
        }
        this.webSocket.send(message);
    }

    /**
     * 断开连接
     */
    public void destroy() {
        if(!this.socketListeners.isEmpty()){
            this.socketListeners.clear();
        }
        if (this.testConnectionRxTimer != null) {
            this.testConnectionRxTimer.cancel();
            this.testConnectionRxTimer = null;
        }
        try {
            if (null != this.webSocket) {
                this.webSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.webSocket = null;
        }
    }

    /**
     * 检测连接
     */
    private void testConnection() {
        this.testConnectionRxTimer = new RxTimer();
        this.testConnectionRxTimer.interval(HEART_BEAT_RATE, number -> {
            if (this.webSocket != null) {
                if (this.webSocket.isClosed()) {
                    Log.e(TAG, " 连接断开");
                    this.reConnect();
                }
            }
        });
    }

    /**
     * 重连
     */
    private synchronized void reConnect() {
        if (this.webSocket != null) {
            try {
                webSocket.reconnectBlocking();
                Log.e(TAG, " 重连");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
