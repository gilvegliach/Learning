package gilvegliach.it.daggerinheritance;

import javax.inject.Inject;

import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    @Inject
    A b;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainComponent component = DaggerMainComponent.create();
        component.inject(this);

        setContentView(R.layout.activity_main);

        TextView text = (TextView) findViewById(R.id.text);
        text.setText(b.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
