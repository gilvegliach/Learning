package it.gilvegliach.contextmanager.contextmanagment;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import it.gilvegliach.contextmanager.R;

/**
 * @author Gil
 */
public abstract class FragmentTask<T extends AppCompatActivity, S extends Fragment>
        extends AsyncTask<Void, Void, Void> {

    private final ActivityProvider<T> mActivityProvider;

    protected FragmentTask(ActivityProvider<T> activityProvider) {
        mActivityProvider = activityProvider;
    }

    final public S getFragment() {
        T activity = mActivityProvider.get();
        if (activity == null) {
            return null;
        }
        FragmentManager fm = activity.getSupportFragmentManager();
        return (S) fm.findFragmentById(R.id.fragment);
    }
}
