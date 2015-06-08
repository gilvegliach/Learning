package gilvegliach.it.daggereagersingleton;

import javax.inject.Inject;

import android.util.Log;

/**
 * @author  Gil Vegliach
 */
public class A {
    @Inject
    public A() {
        Log.d("Dagger", "A.<init>()");
    }
}
