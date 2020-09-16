package com.boreas.plainlife.websocket;


import com.boreas.plainlife.Constant;
import com.boreas.plainlife.utils.PreUtil;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class WebSocketManger {
    private static WebSocketManger mInstance;
    private WebSocket webSocket;
    private OkHttpClient client;

    private WebSocketManger(OkHttpClient client) {
        this.client = client;
    }

    public static WebSocketManger getInstance(OkHttpClient client) {
        if (mInstance == null) {
            synchronized (WebSocketManger.class) {
                if (mInstance == null) {
                    mInstance = new WebSocketManger(client);
                }
            }
        }
        return mInstance;
    }

    public WebSocket getWebSocket() {
        return this.webSocket;
    }

    public void init() {
        Request request = new Request.Builder()
                .url(Constant.WEBSOCKET_URL)
                .header("Authorization", (String) PreUtil.get(Constant.TOKEN_KEY, ""))
                .build();
        this.webSocket = client.newWebSocket(request, new PlainWebSocketListener());
    }

    public void sendMessage(String message) {
        this.webSocket.send(message);
    }

}
