// Ctci 11.6
public class BinarySearchMatrix {
    static class Pair {
        int i;
        int j;
        Pair(int ii, int jj) {
            i = ii;
            j = jj;
        }

        @Override
        public String toString() {
           return "(" + i + ", " + j + ")";
        } 
    }

    static Pair find(int[][] a, int k, int l, int r, int t, int b) {
        //System.out.println("k: " + k + ", l: " + l + ", r: " + r
        //        + ", t: " + t + ", b: " + b);
        if (r < l || b < t) return null;
        if (r - l + 1 == 1 && b - t + 1 == 1) {
            if (a[t][l] == k) return new Pair(t, l);
            else return null;
        } else if (r - l + 1 == 1) {
            int res = vbinsearch(a, k, r, t, b);
            if (res != -1) return new Pair(res, r);
        } else if (b - t + 1 == 1) {
            int res = hbinsearch(a, k, l, r, t);
            if (res != -1) return new Pair(t, res);
        }
        int mv = (t + b) / 2;
        int mh = (l + r) / 2;
        if (a[mv][mh] == k) return new Pair(mh, mv);
        if (a[mv][mh] > k) {
            Pair res = find(a, k, l, mh, t, mv);
            if (res != null) return res;
            res = find(a, k, mh + 1, r, t, mv);
            if (res != null) return res;
            return find(a, k, l, mh, mv + 1, b);
        } else {
            Pair res = find(a, k, mh + 1, r, mv + 1, b);
            if (res != null) return res;
            res = find(a, k, mh + 1, r, t, mv);
            if (res != null) return res;
            return find(a, k, l, mh, mv + 1, b);
        }
    }

    static int vbinsearch(int[][] a, int k, int j, int t, int b) {
        if (b < t) return -1;
        int m = (t + b) / 2;
        if (a[m][j] == k) return m;
        if (a[m][j] > k) return vbinsearch(a, k, j, t, m - 1);
        return vbinsearch(a, k, j, m + 1, b);
    }

    static int hbinsearch(int[][] a, int k, int l, int r, int i) {
        if (r < l) return -1;
        int m = (l + r) / 2;
        if (a[i][m] == k) return m;
        if (a[i][m] > k) return hbinsearch(a, k, l, m - 1, i);
        return hbinsearch(a, k, m + 1, r, i);
    }
    
    public static void main(String[] args) {
        int[][] a = new int[][] {
            { 1, 1, 2, 3 },
            { 2, 2, 3, 3 },
            { 3, 3, 4, 5 },
            { 6, 6, 6, 9 }
        };
        System.out.println("Expected: (2, 3), (0, 0)-(0, 1),"
               + " (3, 0)-(3, 2), (2, 2), (3, 3), null");
        Pair res = find(a, 5, 0, 3, 0, 3);
        System.out.println(res);
        res = find(a, 1, 0, 3, 0, 3);
        System.out.println(res);
        res = find(a, 6, 0, 3, 0, 3);
        System.out.println(res);
        res = find(a, 4, 0, 3, 0, 3);
        System.out.println(res);
        res = find(a, 9, 0, 3, 0, 3);
        System.out.println(res);
        res = find(a, 10, 0, 3, 0, 3);
        System.out.println(res);
    }    
}
