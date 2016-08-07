// ctci 9.8
public class RepresentingNCents {
    // Representing n cents with 25, 10, 5 and 1 coins
    static int rep(int n) {
        int sum = 0;
        for ( ; n >= 0; n -= 25) {
            for (int m = n; m >= 0; m -= 10) {
                for (int k = m; k >= 0; k -= 5) {
                    sum++;
                }
            }
        }
        return sum;
    }

    // f_10(n) = f_5(fl(n/2)) (f_5(n) - f_5(fl(n/2)) + 1)
    // k: f_5(fl(n/2)) = fl(fl(n/2)/5) + 1
    // m: f_5(n) = fl(n/5) + 1
    static int rep2(int n) {
        int sum = 0;
        for ( ; n >= 0; n -= 25) {
            int k = (n / 2) / 5 + 1;
            int m = n / 5 + 1;
            sum += k * (m - k + 1);
        }
        return sum;
    }

    static int rep3(int n) {
        return rep3h(n, 0, new int[]{ 25, 10, 5 });
    }

    static int rep3h(int n, int i, int[] coins) {
        if (i >= coins.length) return 1;
        int c = coins[i];
        int sum = 0;
        for ( ; n >= 0; n -= c) {
            sum += rep3h(n, i + 1, coins);
        }
        return sum;
    }

    public static void main(String[] args) {
        boolean ok = true;
        for (int i = 1; i < 1000; i++) {
            int r1 = rep(i);
            int r2 = rep2(i);
            if (r1 != r2) {
                ok = false;
                System.out.print(
                   String.format("i = %d, r1 = %d, r2 = %d", i, r1, r2));
            }
        }
        if (ok) System.out.println("rep1 = rep2");
        else    System.out.println("ERROR: rep1 != rep2");
        
        for (int i = 1; i < 1000; i++) {
            int r1 = rep(i);
            int r3 = rep3(i);
            if (r1 != r3) {
                ok = false;
                System.out.print(
                   String.format("i = %d, r1 = %d, r3 = %d", i, r1, r3));
            }
        }
        if (ok) System.out.println("rep1 = rep3");
        else    System.out.println("ERROR: rep1 != rep3");
    }
}
