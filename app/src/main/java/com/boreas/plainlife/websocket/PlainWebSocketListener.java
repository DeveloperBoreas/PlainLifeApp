package com.boreas.plainlife.websocket;

import android.util.Log;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class PlainWebSocketListener extends WebSocketListener {

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        Log.e("onOpen: ", "onOpen");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        Log.e("onMessage: ", text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        Log.e("onMessage byteString: ", bytes.toString());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(1000, null);
        Log.e("onClosing: ", code + "/" + reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        Log.e("onClosed: ", code + "/" + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        Log.e("onFailure: ", t.getMessage());
    }

}
