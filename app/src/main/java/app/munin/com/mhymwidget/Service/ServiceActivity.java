package app.munin.com.mhymwidget.Service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import app.munin.com.mhymwidget.R;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ServiceActivity extends FragmentActivity {
    private TextView textView;
    private IService service;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service = IService.Stub.asInterface(iBinder);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (textView != null)
                        if (service != null)
                            try {
                                textView.setText("" + service.showContent());
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            service = null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        textView = (TextView) findViewById(R.id.txt);
    }

    public void click(View view) {
        startService(new Intent("com.munin.test.aidl.service"));
    }

    public void bind(View view) {
        bindService(new Intent("com.munin.test.aidl.service"), connection, Service.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
