package app.munin.com.mhymwidget.Service;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import app.munin.com.mhymwidget.MainActivity;
import app.munin.com.mhymwidget.R;

/**
 * Created by Administrator on 2017/5/4.
 */

public class MNService extends Service {
    Binder binder = new IServiceImp("hello aidl");
    public static final String ONCLICK = "com.app.onclick";
    private NotificationManager notificationManager;
    private static int i = 1;
    private boolean stop = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onbind");
        return binder;
    }

    @Override
    public void onCreate() {
        System.out.println("oncreate");
        stop = false;
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        super.onCreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");
        new Thread(()->showNotice()).start();

        return super.onStartCommand(intent, flags, startId);
    }




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    void showNotice(){
        while (!stop) {
            i++;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Notification.Builder builder = new Notification.Builder(MNService.this);
            IntentFilter filter_click = new IntentFilter();
            filter_click.addAction(ONCLICK);
            //注册广播
            registerReceiver(receiver_onclick, filter_click);
            Intent Intent_pre = new Intent(ONCLICK);
            Intent_pre.putExtra("id", i);
            //得到PendingIntent
            PendingIntent pendIntent_click = PendingIntent.getBroadcast(MNService.this, 0, Intent_pre, 0);
            //设置监听
            builder.setContentIntent(pendIntent_click);
            builder.setSmallIcon(R.mipmap.ic_launcher);// 设置图标
            builder.setContentTitle("标题");// 设置通知的标题
            builder.setContentText("内容");// 设置通知的内容
            builder.setWhen(System.currentTimeMillis());// 设置通知来到的时间
            builder.setTicker("new message");// 第一次提示消失的时候显示在通知栏上的
            builder.setOngoing(true);
            Notification notification = builder.build();
            notification.flags = Notification.FLAG_NO_CLEAR;  //只有全部清除时，Notification才会清除
            notificationManager.notify(i, notification);
        }
    }

    @Override
    public void onDestroy() {
        stop = true;
        super.onDestroy();

    }


    private BroadcastReceiver receiver_onclick = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ONCLICK)) {
                int id = intent.getIntExtra("id", 0);
                if (id != 0) {
                    notificationManager.cancel(id);
                }
            }
        }
    };
}
