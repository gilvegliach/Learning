import java.lang.reflect.*;

public class DynamicProxy {
    interface Foo {
        void doStuff();
        int howMuchStuff();
    }

    static class FooImpl implements Foo {
        @Override
        public void doStuff() {
            System.out.println("Stuff");
        }
        @Override
        public int howMuchStuff() {
            return 42;
        }
    }

    static Foo instrument(final Foo delegate) {
        return (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),
                new Class[] { Foo.class },
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method m,
                            Object[] args) {
                        try {
                            System.out.println("Entering: " + m);
                            return m.invoke(delegate, args);
                        } catch (IllegalAccessException | 
                                IllegalArgumentException |
                                InvocationTargetException  e) {
                            throw new RuntimeException(e);
                        } finally {
                            System.out.println("Exiting: " + m);
                        }
                    }
                });
    }

    public static void main(String[] args) {
        Foo foo = new FooImpl();
        Foo bar = instrument(foo);
        foo.doStuff();
        System.out.println(foo.howMuchStuff());
        bar.doStuff();
        System.out.println(bar.howMuchStuff());
    }
}
