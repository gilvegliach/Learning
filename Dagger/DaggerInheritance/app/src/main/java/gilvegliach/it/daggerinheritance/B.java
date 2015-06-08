package gilvegliach.it.daggerinheritance;

import javax.inject.Inject;

public class B implements A {

    // This will also be injected
    @Inject
    String text;

    @Inject
    public B() { }

    @Override
    public String toString() {
        return text;
    }
}
