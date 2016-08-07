// 17.3
public class ZerosFactorial {
    public static void main(String[] args) {
        int[] tests = { 1, 5, 4, 6, 10, 11, 25, 26, 50 };
        int[] expec = { 0, 1, 0, 1, 2, 2, 6, 6, 12 };
        for (int i = 0; i < tests.length; i++) {
            int res = zeros(tests[i]);
            if (res != expec[i]) {
                System.out.println("Fail for n = " + tests[i] + ", res = " + res);
            }
        }
    }

    static int zeros(int n) {
        int count = 0;
        for (; n >= 5; n /= 5) {
            count += n / 5;
        }
        return count;
    }
}
