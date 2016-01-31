package it.gilvegliach.activitylowmemory;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import it.gilvegliach.activitylowmemory.R;


public class MainActivity extends Activity implements FragmentManager.OnBackStackChangedListener {
    private static final String TAG = "MainActivity";
    private static final String KEY_ACTIVITY_COUNT = ".activity_count";
    private int mActivityCount;
    private int mFragmentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivityCount = getIntent().getIntExtra(KEY_ACTIVITY_COUNT, 1);

        Log.d(TAG, "onCreate(), count: " + mActivityCount);

        FragmentManager fm = getFragmentManager();
        fm.addOnBackStackChangedListener(this);
        Fragment f = fm.findFragmentById(R.id.container);
        if (f == null) {
            mFragmentCount = 1;
            f = MainFragment.newInstance(mFragmentCount);
            fm.beginTransaction().add(R.id.container, f).commit();
        } else {
            mFragmentCount = ((MainFragment) f).getCount();
        }

        TextView tvActivity = (TextView) findViewById(R.id.tv_activity);
        TextView tvFragment = (TextView) findViewById(R.id.tv_fragment);
        tvActivity.setText(String.valueOf(mActivityCount));
        tvFragment.setText(String.valueOf(mFragmentCount));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy(), count: " + mActivityCount);
    }

    @Override
    public void onBackStackChanged() {
        FragmentManager fm = getFragmentManager();
        Fragment f = fm.findFragmentById(R.id.container);
        mFragmentCount = ((MainFragment) f).getCount();
        TextView tv = (TextView) findViewById(R.id.tv_fragment);
        tv.setText(String.valueOf(mFragmentCount));
    }

    public void clickStartActivity(View button) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra(KEY_ACTIVITY_COUNT, mActivityCount + 1);
        startActivity(i);
    }

    public void clickPushFragment(View button) {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance(mFragmentCount + 1))
                .addToBackStack(null)
                .commit();
    }
}
