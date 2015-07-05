package com.example.porterduffexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null)
		{
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment
	{

		public PlaceholderFragment()
		{
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			SeeThroughTextView tv = new SeeThroughTextView(getActivity());
			tv.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			tv.setText("Hello");
			tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 60);
			tv.setBackgroundResource(R.drawable.view_bg);

			// tv.setBackgroundColor(Color.RED);
			return tv;
		}
	}

	final static class SeeThroughTextView extends TextView
	{
		Bitmap mMaskBitmap;
		Canvas mMaskCanvas;
		Paint mPaint;

		Drawable mBackground;
		Bitmap mBackgroundBitmap;
		Canvas mBackgroundCanvas;
		private boolean mSetBoundsOnSizeAvailable = false;

		public SeeThroughTextView(Context context)
		{
			super(context);

			mPaint = new Paint();
			mPaint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
			super.setTextColor(Color.BLACK);
			super.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		}

		@Override
		@Deprecated
		public void setBackgroundDrawable(Drawable bg)
		{
			mBackground = bg;
			int w = bg.getIntrinsicWidth();
			int h = bg.getIntrinsicHeight();

			// Drawable has no dimensions, retrieve View's dimensions
			if (w == -1 || h == -1)
			{
				w = getWidth();
				h = getHeight();
			}

			// Layout has not run
			if (w == 0 || h == 0)
			{
				mSetBoundsOnSizeAvailable = true;
				return;
			}

			mBackground.setBounds(0, 0, w, h);
			invalidate();
		}

		@Override
		public void setBackgroundColor(int color)
		{
			setBackgroundDrawable(new ColorDrawable(color));
		}

		@Override
		protected void onSizeChanged(int w, int h, int oldw, int oldh)
		{
			super.onSizeChanged(w, h, oldw, oldh);
			mBackgroundBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
			mBackgroundCanvas = new Canvas(mBackgroundBitmap);
			mMaskBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
			mMaskCanvas = new Canvas(mMaskBitmap);

			if (mSetBoundsOnSizeAvailable)
			{
				mBackground.setBounds(0, 0, w, h);
				mSetBoundsOnSizeAvailable = false;
			}
		}

		@Override
		protected void onDraw(Canvas canvas)
		{
			// Draw background
			mBackground.draw(mBackgroundCanvas);

			// Draw mask
			mMaskCanvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
			super.onDraw(mMaskCanvas);

			mBackgroundCanvas.drawBitmap(mMaskBitmap, 0.f, 0.f, mPaint);
			canvas.drawBitmap(mBackgroundBitmap, 0.f, 0.f, null);
		}
	}
}
