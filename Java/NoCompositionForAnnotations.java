/*

Prints:

 *** Direct
 @Service()

 *** Recursive
 @Service()
 --@Component()
 ----@java.lang.annotation.Retention(value=RUNTIME)
 ------@java.lang.annotation.Documented()
 --------@java.lang.annotation.Target(value=[ANNOTATION_TYPE])
 ------@java.lang.annotation.Target(value=[ANNOTATION_TYPE])
 ----@java.lang.annotation.Target(value=[TYPE])
 --@java.lang.annotation.Retention(value=RUNTIME)
 --@java.lang.annotation.Target(value=[TYPE])

*/
import java.lang.annotation.*;
import java.util.*;

public class NoCompositionForAnnotations {
    public static void main(String[] args) {
        System.out.println("*** Direct");
        printAnnotationsDirect(MyService.class);
        System.out.println();
        System.out.println("*** Recursive");
        printAnnotationsRecursive(MyService.class);
    }

    static void printAnnotationsDirect(Class<?> cls) {
        for (Annotation a : cls.getAnnotations()) {
            System.out.println(a);
        }
    }

    static void printAnnotationsRecursive(Class<?> cls) {
        printAnnotationsRecursive(cls, new HashSet<Annotation>(), "");
    }

    static void printAnnotationsRecursive(Class<?> cls, HashSet<Annotation> seen, String prefix) {
        Annotation[] annotations = cls.getDeclaredAnnotations();
        HashSet<Annotation> left = new HashSet<Annotation>();
        for (Annotation a : annotations) {
            if (!seen.contains(a)) left.add(a);
        }
        if (left.size() == 0) return;
        for (Annotation a : left) {
            System.out.println(prefix + a);
            seen.add(a);
            printAnnotationsRecursive(a.annotationType(), seen, prefix + "--");
        }
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Component {
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@interface Service {
}

@Service
class MyService {
}
