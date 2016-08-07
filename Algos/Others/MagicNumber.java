import java.util.*;

public class MagicNumber {
    public static long getMagic(int k) {
        if (k < 0) return 0;
        long val = 0;
        Queue<Long> queue3 = new LinkedList<Long>();
        Queue<Long> queue5 = new LinkedList<Long>();
        Queue<Long> queue7 = new LinkedList<Long>();
        queue3.add(1L);

        for (int i = 0; i <= k; i++) {
            long v3 = queue3.size() > 0 ? queue3.peek() :
                Long.MAX_VALUE;
            long v5 = queue5.size() > 0 ? queue5.peek() :
                Long.MAX_VALUE;
            long v7 = queue7.size() > 0 ? queue7.peek() :
                Long.MAX_VALUE;
            val = Math.min(v3, Math.min(v5, v7));
            if (val == v3) {
                queue3.remove();
                queue3.add(3 * val);
                queue5.add(5 * val);
            } else if (val == v5) {
                queue5.remove();
                queue5.add(5 * val);
            } else if (val == v7) {
                queue7.remove();
            }
            queue7.add(7 * val);
        }
        return val;
    }

    static ArrayList<Long> magic = new ArrayList<Long>();
    public static long getMagic2(int k) {
        if (k < magic.size()) {
            return magic.get(k);
        }
        k -= magic.size() - 1;
        while (k-- > 0) {
            next();
        }
        return magic.get(magic.size() - 1);
    }

    static void next() {
        int size = magic.size();
        if (size == 0) {
            magic.add(1L);
            return;
        }
        Set<Long> s = new HashSet<Long>(size * 3);
        for (int i = 0; i < size; i++) {
            long val = magic.get(i);
            s.add(val * 3);
            s.add(val * 5);
            s.add(val * 7);
        }
        for (int i = 0; i < size; i++) {
            s.remove(magic.get(i));
        }
        long min = Long.MAX_VALUE;
        for (Long n : s) {
            if (n < min) min = n;
        }
        magic.add(min);
    }

    public static void main(String[] args) {
        boolean pass = true;
        for (int i = 0; i < 10000; i++) {
            long val1 = getMagic(i);
            long val2 = getMagic2(i);
            if (val1 != val2) {
                pass = false;
                System.out.println("val1=" + val1 + ", val2=" + val2);
            } else {
               // System.out.println("VAL=" + val1);
            }
        }
        System.out.println(pass ? "PASS" : "FAIL");
    }
}
