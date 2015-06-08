package it.gilvegliach.learn.daggerbaseactivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gilmac on 27/05/15.
 */
@Module
public class StringModule {

    @Provides
    public String provideString() {
        return "some text";
    }
}
