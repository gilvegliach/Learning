package it.gilvegliach.lifecycle.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String TAG = "LifecycleActivity";
    private static final String KEY_COUNT = ".count";

    private int mCount;

    public void click(View button) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra(KEY_COUNT, mCount + 1);
        //startActivity(i);
        startActivityForResult(i, mCount);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mCount = getIntent().getIntExtra(KEY_COUNT, 0);
        Log.d(TAG, "onCreate(" + format(savedInstanceState) + "), count: " + mCount);
        TextView tv = (TextView) findViewById(R.id.text);
        tv.setText(String.valueOf(mCount));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart(), count: " + mCount);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart(), count: " + mCount);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume(), count: " + mCount);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause(), count: " + mCount);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop(), count: " + mCount);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy(), count: " + mCount);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult(" + requestCode + ", " + resultCode + ", " + format(data) + "), count: " + mCount);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState(" + format(savedInstanceState) + "), count: " + mCount);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState(" + format(outState) + "), count: " + mCount);
    }

    @Override
    @Deprecated
    public Object onRetainNonConfigurationInstance() {
        Log.d(TAG, "onRetainNonConfigurationInstance(), count: " + mCount);
        return super.onRetainNonConfigurationInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu(" + menu + "), count: " + mCount);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d(TAG, "onPrepareOptionsMenu(" + menu + "), count: " + mCount);
        return true;
    }

    private String format(Object o) {
        if (o == null)
            return "null";
        return "non-null";
    }
}
