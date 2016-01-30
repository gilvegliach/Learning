package it.gilvegliach.contextmanager.contextmanagment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Gil
 */
public class ActivityProviderFragment<T extends AppCompatActivity>
        extends Fragment implements ActivityProvider<T> {

    public static String TAG = ".activity_provider";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public T get() {
        return (T) getActivity();
    }
}
