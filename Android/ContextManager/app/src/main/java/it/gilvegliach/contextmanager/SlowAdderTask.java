package it.gilvegliach.contextmanager;

import android.os.SystemClock;
import android.widget.TextView;

import javax.inject.Inject;

import it.gilvegliach.contextmanager.contextmanagment.ActivityProvider;
import it.gilvegliach.contextmanager.contextmanagment.FragmentTask;

/**
 * @author Gil
 */
public class SlowAdderTask extends FragmentTask<MainActivity, MainFragment> {
    private SlowAdderTask(ActivityProvider<MainActivity> activityProvider) {
        super(activityProvider);
    }

    @Override
    protected Void doInBackground(Void... params) {
        SystemClock.sleep(3000);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        // The only thing we need to do is to ask for a fragment
        MainFragment fragment = getFragment();

        TextView number = fragment.mNumber;
        int value = Integer.valueOf(number.getText().toString());
        number.setText(String.valueOf(value + 1));
    }

    // Not required, but nice to have
    public static class Factory {
        private final ActivityProvider<MainActivity> mActivityProvider;

        @Inject
        public Factory(ActivityProvider<MainActivity> activityProvider) {
            mActivityProvider = activityProvider;
        }

        public FragmentTask<MainActivity, MainFragment> build() {
            return new SlowAdderTask(mActivityProvider);
        }
    }
}
