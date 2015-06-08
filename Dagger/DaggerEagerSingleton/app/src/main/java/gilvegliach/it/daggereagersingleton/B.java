package gilvegliach.it.daggereagersingleton;

import android.util.Log;

import javax.inject.Inject;

/**
 * @author  Gil Vegliach
 */
public class B {
    // This constructor is logged meaning singletons are lazy
    @Inject
    public B() {
        Log.d("Dagger", "B.<init>()");
    }
}
