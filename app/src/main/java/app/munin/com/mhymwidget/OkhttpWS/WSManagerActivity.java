package app.munin.com.mhymwidget.OkhttpWS;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import app.munin.com.mhymwidget.OkhttpWS.OkhttpWSManager.WsManager;
import app.munin.com.mhymwidget.OkhttpWS.OkhttpWSManager.WsStatusListener;
import app.munin.com.mhymwidget.R;
import okhttp3.Response;
import okio.ByteString;

/**
 * Created by Administrator on 2017/5/15.
 */

public class WSManagerActivity extends FragmentActivity {

    private final static String TAG = "MainActivity";
    private WsManager wsManager;
    private int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws);
        wsManager = new WsManager.Builder(this)
                .wsUrl("ws://114.55.107.151:7272")
                .build();


        wsManager.setWsStatusListener(new WsStatusListener() {
            @Override
            public void onOpen(Response response) {
                super.onOpen(response);
                System.out.println("SHUJU："+response.body());
            }
            @Override
            public void onMessage(String text) {
                super.onMessage(text);
                System.out.println("SHUJU1："+text);
            }

            @Override
            public void onMessage(ByteString bytes) {
                super.onMessage(bytes);
            }

            @Override
            public void onReconnect() {
                super.onReconnect();
            }

            @Override
            public void onClosing(int code, String reason) {
                super.onClosing(code, reason);
            }

            @Override
            public void onClosed(int code, String reason) {
                super.onClosed(code, reason);
            }

            @Override
            public void onFailure(Throwable t, Response response) {
                super.onFailure(t, response);
            }
        });

    }

    public void connect(View view) {
        wsManager.startConnect();
    }

    public void disconnect(View view) {
        wsManager.stopConnect();
    }

    public void send(View view) {
        wsManager.sendMessage("{\"type\":\"login\",\"client_name\":\"fsdf\",\"room_id\":\"1\"}");

    }
}
