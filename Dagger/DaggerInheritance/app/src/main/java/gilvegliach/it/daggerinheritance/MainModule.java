package gilvegliach.it.daggerinheritance;

import dagger.Module;
import dagger.Provides;

/**
 * @author  Gil Vegliach <gil.vegliach@zalando.de>
 */
@Module
public class MainModule {

    // Without the explicit binding Dagger cannot provide a B when an A is requested
    @Provides
    public A provideA(final B b) {
        return b;
    }

    @Provides
    public String provideString() {
        return "some text";
    }
}
