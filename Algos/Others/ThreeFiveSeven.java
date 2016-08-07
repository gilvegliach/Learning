import static java.lang.Math.pow;

public class ThreeFiveSeven {
    static class Number {
        final int i;
        final int j;
        final int k;

        private Number(int ii, int jj, int kk) {
            i = ii;
            j = jj;
            k = kk;
        }

        static Number one() {
            return new Number(0, 0, 0);
        }

        Number next() {
            Number[] candidates = new Number[]{
                new Number(i, j, k + 1),
                new Number(i, j + 1, k),
                new Number(i, j + 1, k + 1),
                new Number(i + 1, j, k),
                new Number(i + 1, j, k + 1),
                new Number(i + 1, j + 1, k),
                new Number(i + 1, j + 1, k + 1)
            };
            int minIndex = 0;
            int min = candidates[0].toInt();
            System.out.println(">>> length: " + candidates.length +
                    ", min: " + min + ", index: " + minIndex );
            for (int q = 1; q < candidates.length; q++) {
                int n = candidates[q].toInt();
                System.out.println(">> n: " + n 
                   + ", (" + candidates[q].i 
                   + ", " + candidates[q].j 
                   + ", " + candidates[q].k + ")");
                if (n < min) {
                    min = n;
                    minIndex = q;
                }
            }
            return candidates[minIndex];      
        }
        
        int toInt() {
            return (int) (pow(3, i) * pow(5, j) * pow(7, k));
        }
    }

    public static int get(int k) {
        if (k == 0) return 1;
        Number n = Number.one();
        while (k-- > 0) {
            n = n.next();
        }
        return n.toInt();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.println("" + i + "th number: " + get(i));
        }
    }
}
