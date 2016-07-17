import java.util.*;

// Count inversions in array, i.e. pairs s.t. i < j and a[i] > a[j]
public class Inversions {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(84);  a.add(2);  a.add(37); a.add(3);
        a.add(67);  a.add(82); a.add(19); a.add(97);
        a.add(91);  a.add(63); a.add(27); a.add(6);
        a.add(13);  a.add(90); a.add(63); a.add(89);
        a.add(100); a.add(60); a.add(47); a.add(96);
        a.add(54);  a.add(26); a.add(64); a.add(50);
        a.add(71);  a.add(16); a.add(6);  a.add(40);
        a.add(84);  a.add(93); a.add(67); a.add(85);
        a.add(16);  a.add(22); a.add(60);
        System.out.println(new Inversions().countInversions(a)); // 290
    }

    public int countInversions(ArrayList<Integer> a) {
	    int n = a.size();
	    if (n < 2) return 0;
	    return inv(a, 0, n - 1);
	}

	int inv(ArrayList<Integer> a, int l, int r) {
	    int n = r - l + 1;
	    if (n < 2) return 0;
	    int m = l + (r - l) / 2;
	    int count = inv(a, l, m) + inv(a, m + 1, r);
	    if (l > m) {
	        return count;
	    }
	    int[] b = copyOf(a, m + 1, r);
	    Arrays.sort(b);
	    for (int i = l; i < m + 1; i++) {
            int pos = insertionPos(b, a.get(i));
	        count += pos;
	    }
	    return count;
	}

	int insertionPos(int[] a, int elem) {
	    return insertionPos(a, elem, 0, a.length - 1);
	}

	int insertionPos(int[] a, int elem, int l, int r) {
	    int n = r - l + 1;
	    if (n == 1) {
	        return elem <= a[l] ? l : l + 1;
	    } else if (n == 2) {
	        if (elem <= a[l]) return l;
	        if (elem <= a[l + 1]) return l + 1;
	        return l + 2;
	    }
	    int m = l + (r - l) / 2;
	    return (elem <= a[m]) 
	        ? insertionPos(a, elem, l, m)
	        : insertionPos(a, elem, m + 1, r);
	}

	int[] copyOf(ArrayList<Integer> a, int l, int r) {
	    int n = r - l + 1;
	    int[] res = new int[n];
	    for (int i = 0; i < n; i++) {
	        res[i] = a.get(l + i);
	    }
	    return res;
	}
}