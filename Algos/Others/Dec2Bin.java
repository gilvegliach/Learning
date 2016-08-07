public class Dec2Bin {
    static String d2b(int n) {
        return n == 0 ? "" : d2b(n / 2) + (n % 2);
    }

    public static void main(String[] args) {
        System.out.println(d2b(13));
    }
}
