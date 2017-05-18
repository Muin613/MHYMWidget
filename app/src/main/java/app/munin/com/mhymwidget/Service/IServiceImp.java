package app.munin.com.mhymwidget.Service;

import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;

/**
 * Created by Administrator on 2017/5/5.
 */

public class IServiceImp extends IService.Stub {
    private String content;

    public IServiceImp(String content) {
        this.content = content;
    }

    @Override
    public String showContent() throws RemoteException {
            return content;
    }

    @Override
    public void giveContent(String content) throws RemoteException {
        this.content = content;
    }
}
