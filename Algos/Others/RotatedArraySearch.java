// 11.3
public class RotatedArraySearch {
    static int search(int[] a,int l, int r, int x) {
        while (l <= r) { 
            int m = (l + r) / 2;
            if (a[m] == x) return m;
            if (a[m] < a[r]) {
                if (a[m] < x && x <= a[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            } else {
                // broken if many duplicates here
                if (a[l] <= x && x < a[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
        int res = search(a, 0, a.length - 1, 5);
        System.out.println(res);
        assert res == 8;
    }
}
