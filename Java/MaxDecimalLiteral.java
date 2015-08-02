public class MaxDecimalLiteral {
    public static void main(String[] args) {
        //int n = 2147483648; // compilation error
        int n = 2147483647;
        int k = -2147483648;
        //long m = 2147483648;// compilation error
        long m = 2147483648L;
        System.out.println(n + " " + k + " " + m);
    }
}
