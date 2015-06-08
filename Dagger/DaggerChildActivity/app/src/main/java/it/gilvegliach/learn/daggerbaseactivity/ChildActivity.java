package it.gilvegliach.learn.daggerbaseactivity;

import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;


public class ChildActivity extends BaseActivity {
    @Inject
    String mChildString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Without the following line the string won't be injected (except if we simulate the late bindings)
        component.inject(this);
        TextView tv = (TextView) findViewById(R.id.text);
        tv.setText(mChildString);
    }
}
