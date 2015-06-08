package gilvegliach.it.daggereagersingleton;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author  Gil Vegliach
 */
@Component
public interface MainComponent {
    void inject(MainActivity activity);
}
