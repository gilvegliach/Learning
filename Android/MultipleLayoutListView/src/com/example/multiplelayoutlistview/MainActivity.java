package com.example.multiplelayoutlistview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity
{
    public static final int DEFAULT = 0;
    public static final int HEADER = 1;

    private static final List<Pair<String, Integer>> mData = new ArrayList<Pair<String, Integer>>();
    static
    {
        mData.add(new Pair<String, Integer>("Header 1", HEADER));
        mData.add(new Pair<String, Integer>("Item 1", DEFAULT));
        mData.add(new Pair<String, Integer>("Item 2", DEFAULT));
        mData.add(new Pair<String, Integer>("Item 3", DEFAULT));
        mData.add(new Pair<String, Integer>("Header 2", HEADER));
        mData.add(new Pair<String, Integer>("Item 4", DEFAULT));
        mData.add(new Pair<String, Integer>("Item 5", DEFAULT));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(new MultipleLayoutAdapter(this, mData));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
