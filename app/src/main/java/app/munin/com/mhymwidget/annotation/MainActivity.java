package app.munin.com.mhymwidget.annotation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import app.munin.com.mhymwidget.R;


public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {
    private ArrayList<TextView> textViews = new ArrayList<>();
    LinearLayout linearLayout;
    private AnimationSet set = new AnimationSet(true);
    private AnimationSet set1 = new AnimationSet(true);
    private TextView currentView;
    Filter f1, f2, f3;
    String sql1, sql2, sql3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        linearLayout = (LinearLayout) findViewById(R.id.body);
        intilAnimation();
        intilAnimation1();
        f1=new Filter();
        f1.setId(1);
        f2=new Filter();
        f2.setUserName("lili");
        f3=new Filter();
        f3.setCity("tian");

    }


    public void add(View view) {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(500, 200);
        textView.setGravity(Gravity.CENTER);
        if (textViews.size() % 2 == 0)
            textView.setBackgroundColor(Color.parseColor("#ffff00"));
        else
            textView.setBackgroundColor(Color.parseColor("#ff0000"));
        textView.setText("" + textViews.size()+"什么鬼");
        textView.setLayoutParams(lp);
//        textView.setVisibility(View.GONE);
        currentView = textView;
        linearLayout.addView(textView);
        textViews.add(textView);
        if (textViews.size() % 2 == 0) {
            textView.clearAnimation();
            TranslateAnimation translateAnimation = new TranslateAnimation(500, 0, 0, 0);
            translateAnimation.setDuration(500);
            textView.startAnimation(translateAnimation);
            System.out.println("来了");
        } else {
            textView.clearAnimation();
            TranslateAnimation translateAnimation = new TranslateAnimation(-500, 0, 0, 0);
            translateAnimation.setDuration(500);
            textView.startAnimation(translateAnimation);
            System.out.println("来了1");
        }

    }


    public void delete(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(5000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                linearLayout.removeAllViews();
                textViews.clear();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        linearLayout.startAnimation(alphaAnimation);

    }

    void intilAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(-500, 0, 0, 0);
        translateAnimation.setDuration(500);
        set.setInterpolator(new LinearInterpolator());
        set.addAnimation(translateAnimation);
        set.setAnimationListener(this);
    }

    void intilAnimation1() {
        TranslateAnimation translateAnimation = new TranslateAnimation(500, 0, 0, 0);
        translateAnimation.setDuration(500);
        set1.setInterpolator(new LinearInterpolator());
        set1.addAnimation(translateAnimation);
        set1.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        if (currentView != null)
            currentView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (currentView != null)
            currentView = null;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
