interface A { void foo(); }

interface B { void bar(); }

interface C extends A, B { void baz(); }

class MultipleExtendsInterfaces implements C {
    public void foo() { }
    public void bar() { }
    public void baz() { }
}
