package it.gilvegliach.activitylowmemory;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import it.gilvegliach.activitylowmemory.R;

import static android.widget.FrameLayout.LayoutParams.WRAP_CONTENT;
import static android.widget.FrameLayout.LayoutParams.MATCH_PARENT;

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    private static final String KEY_COUNT = ".fragment_count";
    private int mCount;

    public static MainFragment newInstance(int count) {
        Bundle args = new Bundle(1);
        args.putInt(KEY_COUNT, count);
        MainFragment f = new MainFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        mCount = args.getInt(KEY_COUNT, 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, "onCreateView(), count: " + mCount);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        ImageView iv = new ImageView(getActivity());
        iv.setLayoutParams(lp);
        iv.setImageBitmap(BitmapFactory.decodeResource(getResources(), getResId()));
        return iv;
    }

    private int getResId() {
        // R.drawable.large
        // Jyothis / CC-BY-SA-3.0,
        // Author: https://commons.wikimedia.org/wiki/User:Jyothis
        // License: http://creativecommons.org/licenses/by-sa/3.0/legalcode
        // Original link: https://commons.wikimedia.org/wiki/File:Chess_Large.JPG

        // R.drawable.large2
        // Editor at Large / CC-BY-SA-2.5
        // Author: https://commons.wikimedia.org/wiki/User:Editor_at_Large
        // License: https://creativecommons.org/licenses/by-sa/2.5/legalcode
        // Original link: https://commons.wikimedia.org/wiki/File:Chocolate_chip_cookie_closeup.jpg
        return mCount % 2 == 0 ? R.drawable.large
                : R.drawable.large2;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView(), count: " + mCount);
    }

    public int getCount() {
        return mCount;
    }
}
