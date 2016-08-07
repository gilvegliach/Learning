public class RotatedMinimum {
    // Assumption: a[]: incr sorted rotated array with unique elems
    public static int min(int[] a) {
        int l = 0;
        int r = a.length - 1;
        while (l < r && a[r] < a[l]) {
            int m = (l + r) / 2;
            if (a[m] >= a[l]) 
                l = m + 1;
            else 
                r = m;
        }
        return a[l];
    }

    public static void main(String[] args) {
        int[] a1 = new int[] { 6, 7, 1, 2, 3, 4, 5 };
        int[] a2 = new int[] { 54, 63, 72, 2, 19, 27, 36, 44 };
        System.out.println("Min(a1) = " + min(a1));
        System.out.println("Min(a2) = " + min(a2));
    }
}
