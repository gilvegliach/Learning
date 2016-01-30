package it.gilvegliach.contextmanager.di;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Gil
 */
@Qualifier
@Retention(RUNTIME)
public @interface PerActivityInstance {
}
