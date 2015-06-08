package it.gilvegliach.learn.daggerbaseactivity;

import dagger.Component;

@Component(modules = StringModule.class)
public interface StringComponent {
    void inject(BaseActivity activity);
    void inject(ChildActivity activity);
}
