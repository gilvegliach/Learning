import static java.lang.Math.min;
import static java.lang.Math.abs;
import java.util.*;

// ctci 18.5
public class WordDistance {
    static int dst(Iterable<String> collection, String a, String b) {
        if (a.equals(b)) return 0;
        Iterator<String> i = collection.iterator();
        int dst = Integer.MAX_VALUE;
        int pos = 0;
        int posA = -1;
        int posB = -1;
        while (i.hasNext()) {
            String c = i.next();
            if (a.equals(c)) {
                posA = pos;
                if (posB != -1) {
                    dst = min(dst, abs(posA - posB));
                    posB = -1;
                }
            } else if (b.equals(c)) {
                posB = pos;
                if (posA != -1) {
                    dst = min(dst, abs(posA - posB));
                    posA = -1;
                }
            }
            pos++;
        }
        return dst;
    }

    public static void main(String[] args) {
        String[] a = { "cat", "house", "cat", "bat", "mice", "house", 
            "dog", "lake", "cat", "house", "mansion", "dog" };
        int res = dst(Arrays.asList(a), "cat", "dog");
        System.out.println(res);
        assert res == 2;
    }
}
