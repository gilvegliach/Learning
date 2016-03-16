// Outputs:
// $ java VarargsNull
// null
// null
// [Ljava.lang.String;@4bbf8a41
//
public class VarargsNull {
    static void foo(String... arr) {
        System.out.println(arr);
    }

    public static void main(String[] args) {
        foo(null);
        foo((String[]) null);
        foo((String) null);
    }
}
