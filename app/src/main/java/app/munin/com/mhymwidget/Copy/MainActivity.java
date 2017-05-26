package app.munin.com.mhymwidget.Copy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.munin.com.mhymwidget.R;
import app.munin.com.mhymwidget.ZoomImage.MoveUpDownFrameLayout;

/**
 * Project Name app.munin.com.mhymwidget.Copy
 * Class Description:
 * Create By : Administrator
 * Create Time: 2017/5/26 14:19
 * Modify by : Administrator
 * Modify Time: 2017/5/26 14:19
 * Modify Remarks:
 */

public class MainActivity extends AppCompatActivity {
    MoveUpDownFrameLayout fl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        fl= (MoveUpDownFrameLayout) findViewById(R.id.fl);
        fl.setFinishListener(new MoveUpDownFrameLayout.Finish() {
            @Override
            public void onFinish() {
                System.out.println(" over ");
            }

            @Override
            public void onRatio(float f) {
                System.out.println("ratio: "+f);
            }
        });
    }
}
