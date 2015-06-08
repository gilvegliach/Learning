package gilvegliach.it.daggerinheritance;

import dagger.Component;

/**
 * @author  Gil Vegliach <gil.vegliach@zalando.de>
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);

    A a();
}
