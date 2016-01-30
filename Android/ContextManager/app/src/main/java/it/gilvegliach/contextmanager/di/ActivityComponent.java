package it.gilvegliach.contextmanager.di;

import dagger.Component;
import it.gilvegliach.contextmanager.MainActivity;
import it.gilvegliach.contextmanager.MainFragment;

/**
 * @author Gil
 */
@PerActivityInstance
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);

    void inject(MainFragment fragment);
}
