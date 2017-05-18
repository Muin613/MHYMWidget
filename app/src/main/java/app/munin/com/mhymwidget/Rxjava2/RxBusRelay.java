package app.munin.com.mhymwidget.Rxjava2;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * Created by Administrator on 2017/5/17.
 */

public class RxBusRelay {
    //这个是背压设计，可以解决订阅者处理事件出现异常后，订阅者无法再收到事件。
    private final Relay<Object> mBus;

    private RxBusRelay() {
        // toSerialized method made bus thread safe
        mBus = PublishRelay.create().toSerialized();
    }

    public static RxBusRelay get() {
        return Holder.BUS;
    }

    public void post(Object obj) {
        mBus.accept(obj);
    }

    public Flowable<Object> asFlowable() {
        return mBus.toFlowable(BackpressureStrategy.LATEST);
    }
//    我还没发现该方法的优缺点，暂时可用，等我深入rxjava4个subject再优化
    public Flowable register(Class eventType) {
        return mBus.toFlowable(BackpressureStrategy.LATEST).ofType(eventType);
    }

    public boolean hasObservers() {
        return mBus.hasObservers();
    }

    private static class Holder {
        private static final RxBusRelay BUS = new RxBusRelay();
    }
}
