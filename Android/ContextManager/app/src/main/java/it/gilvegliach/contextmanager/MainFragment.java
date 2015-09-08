package it.gilvegliach.contextmanager;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

public class MainFragment extends Fragment implements View.OnClickListener {
    @Inject
    SlowAdderTask.Factory mTaskFactory;

    TextView mNumber;
    Button mButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        activity.getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mNumber = (TextView) v.findViewById(R.id.number);
        mButton = (Button) v.findViewById(R.id.button);
        mButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        mTaskFactory.build().execute();
    }
}
