package app.munin.com.mhymwidget.Copy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import app.munin.com.mhymwidget.R;
import app.munin.com.mhymwidget.ZoomImage.MoveUpDownFrameLayout;
import app.munin.com.mhymwidget.ZoomImage.ZoomImageView;

public class ViewPagerActivity extends SwipeBackActivity {
	private ViewPager viewPager;
	private List<View> list = new ArrayList<View>();
	private MoveFrameLayout.Callback back=new MoveFrameLayout.Callback() {
		@Override
		public boolean canMoveLeftOrRight(boolean flag) {
			return flag;
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager);
		byte[] bytes=getIntent().getByteArrayExtra("bitmap");
		Bitmap bitmap= BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		System.out.println(bytes.length);
		viewPager = (ViewPager) findViewById(R.id.frame_main_viewpage);
		Random rand = new Random();
		setMove(back);
		for(int i=0; i<5; i++){


			View v= LayoutInflater.from(this).inflate(R.layout.activity_move,null);
			v.setBackgroundColor(Color.rgb(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
			((MoveUpDownFrameLayout)v.findViewById(R.id.fl)).setFinishListener(new MoveUpDownFrameLayout.Finish() {
				@Override
				public void onFinish() {
					System.out.println(" over ");
					ViewPagerActivity.this.finish();
					layout.finish();
				}

				@Override
				public void onRatio(float f) {
					System.out.println("ratio: "+(1-f)*255);
					v.setAlpha((1-f));
				}
			});

			((ZoomImageView)v.findViewById(R.id.img_scale)).setBack(new ZoomImageView.Original() {
				@Override
				public void onBack() {
					back.canMoveLeftOrRight(true);
					System.out.println("nengdong");
				}

				@Override
				public void onNo() {
					back.canMoveLeftOrRight(false);
					System.out.println("bunengdong");
				}
			});
			list.add(v);
		}
		
		System.out.println(list.size());
		
		viewPager.setAdapter(new Adapter(this, list));
	}
	
	
	
	public class Adapter extends PagerAdapter{
		private List<View> list;
		
		public Adapter(Context context, List<View> list){
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager)container).removeView(list.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View v = list.get(position);
			((ViewPager)container).addView(v);
			return v;
		}

		
		
	}


}
