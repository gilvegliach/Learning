package it.gilvegliach.contextmanager.contextmanagment;

import android.support.v7.app.AppCompatActivity;

/**
 * @author Gil
 */
public interface ActivityProvider <T extends AppCompatActivity> {
    T get();
}
