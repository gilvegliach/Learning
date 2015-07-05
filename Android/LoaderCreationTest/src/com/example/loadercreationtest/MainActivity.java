package com.example.loadercreationtest;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Void>
{
    static final String TAG = "LoaderTest";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_main);

        LoaderManager lm = getLoaderManager();
        lm.initLoader(1, null, this);
    }

    @Override
    protected void onStart()
    {
        Log.d(TAG, "onStart()");
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public Loader<Void> onCreateLoader(int id, Bundle args)
    {
        Toast.makeText(this, "Loader created", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Loader created");
        return new MyLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Void> arg0, Void arg1)
    {
        Toast.makeText(this, "Load finished", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Load finished");
    }

    @Override
    public void onLoaderReset(Loader<Void> arg0)
    {
    }
}

class MyLoader extends AsyncTaskLoader<Void>
{

    public MyLoader(Context context)
    {
        super(context);
    }

    @Override
    protected void onStartLoading()
    {
        Log.d(MainActivity.TAG, "Force loading");
        forceLoad();
    }

    @Override
    public Void loadInBackground()
    {
        Log.d(MainActivity.TAG, "Start sleeping...");
        // Only sleeps...
        try
        {
            Thread.sleep(5 * 1000);
        }
        catch (InterruptedException e)
        {
        }

        Log.d(MainActivity.TAG, "Stop sleeping");
        return null;
    }

}