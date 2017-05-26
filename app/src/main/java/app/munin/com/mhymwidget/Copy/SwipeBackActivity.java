package app.munin.com.mhymwidget.Copy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import app.munin.com.mhymwidget.R;

/**
 * 
 * @author xiaanming
 *
 */
public class SwipeBackActivity extends Activity {
	protected MoveFrameLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = (MoveFrameLayout) LayoutInflater.from(this).inflate(
				R.layout.base, null);
		layout.attachToActivity(this);
	}

	public void init(float f){
		layout.setAlpha(f);
	}
	public void setMove(MoveFrameLayout.Callback callback){
		layout.setCallback(callback);
	}
	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
	}




	// Press the back button in mobile phone
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(0, R.anim.base_slide_right_out);
	}


}
