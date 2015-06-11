package it.gilvegliach.learn.replacefragmentwithitself;

import android.os.Bundle;

import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author  Gil Vegliach <gil.vegliach@zalando.de>
 */
public abstract class BaseFragment extends Fragment {

    public abstract int color();

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View v = new View(getActivity());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        v.setBackgroundColor(color());
        v.setLayoutParams(lp);
        return v;
    }
}
