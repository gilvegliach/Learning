// ctci 18.2
public class SumMatrix {
    public static void main(String[] args) {
        int[][] a = {
            {-5, -5, -5, -5},
            {-5,  1,  3,  1},
            {-5, -1, -3,  2},
            {-5,  1,  1,  1}
        };
        int sum = maxSum(a);
        System.out.println(sum);
        assert sum == 6;
    }

    static int maxSum(int[][] a) {
        int n = a.length;
        int[][] b = new int[n][n];
        for (int i = 0; i < n; i++) {
            b[i][0] = a[i][0];
            for (int j = 1; j < n; j++) {
                b[i][j] = b[i][j - 1] + a[i][j];
            }
        }

        int res = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++) {
            if (j > 0) {
                for (int i = 0; i < n; i++) {
                    int delta = b[i][j - 1];
                        for (int k = j; k < n; k++) {
                            b[i][k] -= delta;
                        }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int q = j; q < n; q++) {
                    int sum = 0;
                    for (int k = i; k < n; k++) {
                        sum += b[k][q];
                        if (sum > res) res = sum;
                        if (sum < 0) break;
                    }
                }
            }
        }
        return res;
    }
}



