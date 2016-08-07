public class SwapInPlace {
    public static void main(String[] args) {
        int a = 3;
        int b = 6;
        p(a, b);
        a ^= b;
        b ^= a;
        a ^= b;
        p(a, b);
        a = a + b;
        b = a - b;
        a = a - b;
        p(a, b);
        a = a * b;
        b = a / b;
        a = a / b;
        p(a, b);
    }

    static void p(int a, int b) {
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println();
    }
}
