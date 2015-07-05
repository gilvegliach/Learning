package com.example.multiplelayoutlistview;

import java.util.List;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MultipleLayoutAdapter extends ArrayAdapter<Pair<String, Integer>>
{
    public MultipleLayoutAdapter(Context context, List<Pair<String, Integer>> objects)
    {
        super(context, R.layout.default_item, objects);
    }

    @Override
    public int getItemViewType(int position)
    {
        return getItem(position).second;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final TextView tv;
        final LayoutInflater inflater = LayoutInflater.from(getContext());
        final int type = getItemViewType(position);

        switch (type)
        {
            case MainActivity.HEADER:
                tv = (TextView) inflater.inflate(R.layout.header_item, parent, false);
                break;
            default:
                tv = (TextView) inflater.inflate(R.layout.default_item, parent, false);
                tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_unknown, 0, 0, 0);
                break;
        }

        tv.setText(getItem(position).first);
        return tv;
    }
}
