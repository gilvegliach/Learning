package com.example.activityinfo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		tv.setLayoutParams(lp);
		float density = getDensity();
		int padding = (int) (16 * density + .5f);
		tv.setPadding(padding, padding, padding, padding);
		tv.setTextSize(18);

		int mc = getMemoryClass();
		StringBuilder sb = new StringBuilder();
		sb.append("Memory Class: ").append(mc).append(" mb\n");
		sb.append("Display density: ").append(density).append("\n");
		tv.setText(sb.toString());
		setContentView(tv);
	}

	int getMemoryClass()
	{
		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		return am.getMemoryClass();
	}

	float getDensity()
	{
		return getResources().getDisplayMetrics().density;
	}
}
