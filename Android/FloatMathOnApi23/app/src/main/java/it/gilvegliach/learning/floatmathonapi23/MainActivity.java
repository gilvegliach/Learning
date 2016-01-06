package it.gilvegliach.learning.floatmathonapi23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Method;

// FloatMath has been removed from the public api, using the
// custom @removed "javadoc" and letting the methods forward
// to Math instead of being native. Although it cannot be 
// compiled against, the method can be accessed through
// reflection.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Class clazz = Class.forName("android.util.FloatMath");
            for (Method m : clazz.getMethods()) {
                Log.d("Gil", "Method:" + m);
            }
            for (Method m : clazz.getDeclaredMethods()) {
                Log.d("Gil", "Declared Method:" + m);
            }
            Method method = clazz.getMethod("sqrt", float.class);
            Float result = (Float) method.invoke(null, 4.f);
            Log.d("Gil", "FloatMath is here, sqrt(4.f) = " + result);
        } catch (Exception e) {
            Log.d("Gil", "FloatMath is gone");
            e.printStackTrace();
        }
    }
}
