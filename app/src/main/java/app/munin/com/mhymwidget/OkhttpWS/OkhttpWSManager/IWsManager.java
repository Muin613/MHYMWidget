package app.munin.com.mhymwidget.OkhttpWS.OkhttpWSManager;

import okhttp3.WebSocket;
import okio.ByteString;



interface IWsManager {
    WebSocket getWebSocket();

    void startConnect();

    void stopConnect();

    boolean isWsConnected();

    int getCurrentStatus();

    boolean sendMessage(String msg);

    boolean sendMessage(ByteString byteString);
}
