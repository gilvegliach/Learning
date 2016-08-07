import java.util.*;

// ctci 18.9
public class Median {
    final PriorityQueue<Double> left; 
    final PriorityQueue<Double> right; 

    public Median(int initialSize) {
        left = new PriorityQueue<Double>(initialSize, 
                Collections.reverseOrder());
        right = new PriorityQueue<Double>(initialSize);
    }

    public void insert(double d) {
        if (left.isEmpty()) {
            left.offer(d);
            return;
        }
        double maxLeft = left.peek();
        if (d <= maxLeft) {
            left.offer(d);
        } else {
            right.offer(d);
        }
        balance();
    }

    private void balance() {
        int sizeLeft = left.size();
        int sizeRight = right.size();
        if (sizeLeft > sizeRight + 1) {
            double d = left.poll();
            right.offer(d);
        } else if (sizeRight > sizeLeft + 1) {
            double d = right.poll();
            left.offer(d);
        }
    }

    public double median() {
        // forgot to check empty case...
        int sizeLeft = left.size();
        int sizeRight = right.size();
        if (sizeLeft > sizeRight) {
            return left.peek();
        } else if (sizeRight > sizeLeft) {
            return right.peek();
        } else {
            return (left.peek() + right.peek()) / 2;
        }
    }

    static boolean equals(double a, double b) {
        return Math.abs(a - b) < 0.1;
    }

    public static void main(String[] args) {
        double[] a = { 7., 8., 2., 4., 6., 1., 9. };
        double[] r = { 7., 7.5, 7., 5.5, 6., 5., 6. };
        Median q = new Median(a.length);
        System.out.println(Arrays.toString(a));
        for (int i = 0; i < a.length; i++) {
            q.insert(a[i]);
            double median = q.median();
            System.out.println("actual for index " 
                    + i + ", median: " + median);
            assert equals(median, r[i]);
        }
    }
}
