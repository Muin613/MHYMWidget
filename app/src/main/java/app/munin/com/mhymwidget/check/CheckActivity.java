package app.munin.com.mhymwidget.check;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import app.munin.com.mhymwidget.R;

/**
 * Created by Administrator on 2017/5/8.
 */

public class CheckActivity extends FragmentActivity {
    private TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_phone);
        title= (TextView) findViewById(R.id.txt);
        title.setText(""+android.os.Build.MANUFACTURER);
    }
}
