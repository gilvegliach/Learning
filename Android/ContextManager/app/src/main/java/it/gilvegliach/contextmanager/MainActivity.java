package it.gilvegliach.contextmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import it.gilvegliach.contextmanager.di.ActivityComponent;
import it.gilvegliach.contextmanager.di.ActivityModule;
import it.gilvegliach.contextmanager.di.DaggerActivityComponent;

public class MainActivity extends AppCompatActivity {
    ActivityComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public ActivityComponent getComponent() {
        if (mComponent == null) {
            mComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return mComponent;
    }
}
