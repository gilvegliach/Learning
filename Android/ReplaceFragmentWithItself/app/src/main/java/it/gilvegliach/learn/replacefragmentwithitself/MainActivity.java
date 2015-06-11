package it.gilvegliach.learn.replacefragmentwithitself;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v7.app.ActionBarActivity;

import android.view.Menu;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.red);
        if (f == null) {
            fm.beginTransaction().add(R.id.red, new RedFragment()).commit();
        }

        // After rotation *green* fragment disappears
        f = fm.findFragmentById(R.id.yellow);
        if (f == null) {
            f = new YellowFragment();
        }

        // This line replaces the yellow fragment with itself, but the green later disappears!
        fm.beginTransaction().replace(R.id.yellow, f).commit();

        f = fm.findFragmentById(R.id.green);
        if (f == null) {
            fm.beginTransaction().add(R.id.green, new GreenFragment()).commit();
        }

        f = fm.findFragmentById(R.id.blue);
        if (f == null) {
            fm.beginTransaction().add(R.id.blue, new BlueFragment()).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
