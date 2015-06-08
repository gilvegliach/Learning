package it.gilvegliach.learn.daggerbaseactivity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import javax.inject.Inject;

/**
 * Created by gilmac on 27/05/15.
 */
public class BaseActivity extends ActionBarActivity {
    @Inject
    String mBaseClassString;

    protected StringComponent component;

    @Override
    protected void onCreate(Bundle saved) {
        super.onCreate(saved);
        component = DaggerStringComponent.create();
        component.inject(this);
        // Use this to simulate late bindings
//        if (this instanceof ChildActivity) {
//            component.inject((ChildActivity) this);
//        } else {
//            component.inject((BaseActivity) this);
//        }
    }
}
