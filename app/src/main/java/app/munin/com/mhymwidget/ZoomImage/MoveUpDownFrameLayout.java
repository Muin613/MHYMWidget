package app.munin.com.mhymwidget.ZoomImage;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Project Name app.munin.com.mhymwidget.ZoomImage
 * Class Description:
 * Create By : Administrator
 * Create Time: 2017/5/26 13:50
 * Modify by : Administrator
 * Modify Time: 2017/5/26 13:50
 * Modify Remarks:
 */

public class MoveUpDownFrameLayout extends FrameLayout {
    private static final String TAG = MoveUpDownFrameLayout.class.getSimpleName();

    private int mTouchSlop;
    private int downX;
    private int downY;
    private int tempX;
    private int tempY;
    private Scroller mScroller;
    private int viewWidth;
    private int viewHeight;
    private boolean isSilding;
    private boolean isFinish;
    private ImageView photo;
    private LinearLayout ll;


    public MoveUpDownFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoveUpDownFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context);
    }


    /**
     * 事件拦截操作
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = tempX = (int) ev.getRawX();
                downY = tempY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (callback != null)
                    if (!callback.canmove(false))
                        return true;

                int moveY = (int) ev.getRawY();
                // 满足此条件屏蔽SildingFinishLayout里面子类的touch事件
                if (moveY - downY > mTouchSlop
                        && Math.abs((int) ev.getRawX() - downX) < mTouchSlop) {
                    return true;
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) event.getRawY();
                int deltaY = tempY - moveY;
                tempY = moveY;
                if (moveY - downY > mTouchSlop
                        && Math.abs((int) event.getRawX() - downX) < mTouchSlop)
                    isSilding = true;

                if (moveY - downY < -mTouchSlop && Math.abs((int) event.getRawX() - downX) < mTouchSlop)
                    isSilding = true;
                System.out.println("shenmegui " + moveY + " " + downY);
                if (Math.abs(moveY - downY) >= 0 && isSilding) {
                    if (moveY - downY < viewHeight || moveY - downY > -viewHeight) {
                        ll.scrollBy(0, deltaY);
                        if (finish != null)
                            finish.onRatio((float) (Math.abs(moveY - downY)) / ((float) viewHeight * 2 / 5) > 1 ? 1 : (float) Math.abs(moveY - downY) / ((float) viewHeight * 2 / 5));
                    }
                } else {
                    ll.scrollTo(0, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                isSilding = false;
                if (ll.getScrollY() <= -viewHeight * 2 / 5 || ll.getScrollY() >= viewHeight * 2 / 5) {
                    isFinish = true;
                    if (finish != null)
                        finish.onFinish();
                    scroll();
                } else {
                    scrollOrigin();
                    isFinish = false;
                }
                break;
        }

        return true;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            viewWidth = this.getWidth();
            viewHeight = this.getHeight();
            if (getChildCount() == 1 && getChildAt(0) instanceof ImageView)
                ll = (LinearLayout) this.getParent();

        }
    }


    /**
     * 滚动出界面
     */
    private void scroll() {
        final int delta;
        if (ll.getScrollY() > 0)
            delta = (viewHeight + ll.getScrollY());
        else
            delta = -viewHeight + ll.getScrollY();
        // 调用startScroll方法来设置一些滚动的参数，我们在computeScroll()方法中调用scrollTo来滚动item
        mScroller.startScroll(0, ll.getScrollY(), 0, delta,
                Math.abs(delta));
        postInvalidate();

    }

    /**
     * 滚动到起始位置
     */
    private void scrollOrigin() {
        int delta = ll.getScrollY();
        mScroller.startScroll(0, ll.getScrollY(), 0, -delta,
                Math.abs(delta));
        postInvalidate();
    }

    @Override
    public void computeScroll() {
        // 调用startScroll的时候scroller.computeScrollOffset()返回true，
        if (mScroller.computeScrollOffset()) {
            ll.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();

            if (mScroller.isFinished() && isFinish) {
                if (finish != null)
                    finish.onFinish();
                System.out.println("jieshu");
            }
        }
    }

    Callback callback;
    Finish finish;

    public void setMoveListener(Callback callback) {
        this.callback = callback;

    }

    public void setFinishListener(Finish finishListener) {
        finish = finishListener;
    }

    public interface Callback {
        boolean canmove(boolean move);
    }

    public interface Finish {
        void onFinish();

        void onRatio(float f);
    }
}