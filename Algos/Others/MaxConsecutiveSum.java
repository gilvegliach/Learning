import java.util.*;

// ctci 17.8
public class MaxConsecutiveSum {
    static int maxSum(int[] a) {
        int n = a.length;
        if (n == 1) return a[0];
        int[] partial = new int[n];
        partial[0] = a[0];
        for (int i = 1; i < n; i++) {
            partial[i] = partial[i - 1] + a[i];
        }
        int max = Integer.MIN_VALUE;
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(n + 1);
        q.add(0);
        for (int i = 0; i < n; i++) {
            int sum = partial[i] - q.peek();
            if (sum > max) max = sum;
            q.add(partial[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = { 2, -8, 3, -2, 4, -10 };
        System.out.println(maxSum(a)); // 5
        a = new int[]{ 2, 8, 3, 2, 4, 10 };
        System.out.println(maxSum(a)); // 29
    }
}
