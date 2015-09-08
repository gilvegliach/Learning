package it.gilvegliach.contextmanager.di;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import it.gilvegliach.contextmanager.contextmanagment.ActivityProvider;
import it.gilvegliach.contextmanager.contextmanagment.ActivityProviderFragment;
import it.gilvegliach.contextmanager.MainActivity;

/**
 * @author Gil
 */
@Module
public class ActivityModule {
    AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Provides
    public AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    public ActivityProvider<MainActivity> provideMainActivityProvider(AppCompatActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment f = fm.findFragmentByTag(ActivityProviderFragment.TAG);
        if (f == null) {
            f = new ActivityProviderFragment<MainActivity>();
            fm.beginTransaction()
                    .add(f, ActivityProviderFragment.TAG)
                    .commit();
        }
        return (ActivityProvider<MainActivity>) f;
    }
}
