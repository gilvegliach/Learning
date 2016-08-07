public class NQueens {
    static int queens(int n) {
        int[] q = new int[n];
        return queensHelper(q, 0);
    }

    static int queensHelper(int[] q, int k) {
        int n = q.length;
        if (k == n) {
            printQueens(q);
            return 1;
        }
        boolean[] attacked = new boolean[n];
        for (int i = 0; i < k; i++) {
            attacked[q[i]] = true;
            int d = k - i;
            if (q[i] - d >= 0) attacked[q[i] - d] = true;
            if (q[i] + d < n) attacked[q[i] + d] = true;
        }
        int sol = 0;
        for (int i = 0; i < n; i++) {
            if (!attacked[i]) {
                q[k] = i;
                sol += queensHelper(q, k + 1);
            }
        }
        return sol;
    }

    static void printQueens(int[] q) {
        int n = q.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == q[i]) System.out.print("q ");
                else System.out.print(". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println(queens(4)); 
        System.out.println(queens(8)); 
    }
}
