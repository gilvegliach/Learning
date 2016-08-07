// Ex: 9.3
// Proved: distinct + ex magic index => magic index at first positive 
// value
// This algo works also with duplicates elements
public class MagicIndex {
	public static void main(String[] args) {
		//int[] arr = new int[] { -2, -1, 2, 4, 6 };
		//System.out.println(magicIndex(arr));
		//arr = new int[] { -4, -3, -2 };
		//System.out.println(magicIndex(arr));
		//arr = new int[] { -4, -3, -2, -1, 4 };
		//System.out.println(magicIndex(arr));
		//arr = new int[] { 2, 2, 2, 2 };
		//System.out.println(magicIndex(arr));
		//arr = new int[] { -2, -2, -2, 3 };
		//System.out.println(magicIndex(arr));
		int[] a = new int[] { -10, -5, -2, -2, -2, 6, 6, 8, 9, 12, 13};
		System.out.println(magic2(a));
	}

    /** Return a magic index or -1 if there is none */
    public static int magicIndex(int[] arr) {
		if (arr == null || arr.length == 0) return -1;
		return magic(arr, 0, arr.length - 1);
	}
	
	// -10  -5 -2 -2 -2  6  6  8  9  12  13
	//   0   1  2  3  4  5  6  7  8   9  10
	public static int magic2(int[] a) {
	    if (a == null || a.length == 0) return -1;
	    int len = a.length;
	    if (len == 1) return a[0] == 0 ? 0 : -1;
	    // len >= 2
	    if (a[0] >= 0) return magicPositive(a, 0, len-1);
	    // a[0] < 0
	    if (a[len-1] < 0) return -1;
	    // a[0] < 0 <= a[len-1]
	    int l = firstPositive(a, 0, len-1);
	    return magicPositive(a, l, len-1);
	}
	
	// -10  -5 -2 -2 -2  6  6  8  9  12  13
	//   0   1  2  3  4  5  6  7  8   9  10
	//  5, 10
	//  6, 10 -> 6
	
	// Assumes a[l] >= l >= 0  
	private static int magicPositive(int[] a, int l, int r) {
	    if (l > r) return -1;
	    // Invariant: returns or maitains a[l] > l
	    if (a[l] == l) return l;
	    return magicPositive(a, a[l], r); 
	}
	
	// -10  -5 -2 -2 -2  6  6  8  9  12  13
	//   0   1  2  3  4  5  6  7  8   9  10
	//  0, 10  len: 11 m: 5
	//  0,  5  len: 6  m: 2
	//  2,  5  len: 4  m: 3
	//  3,  5  len: 3  m: 4
	//  4,  5  len: 2 -> 5
	// Assumes a[l] < 0 <= a[r]
	private static int firstPositive(int[] a, int l, int r) {
	    int len = r - l + 1;
	    if (len < 0) return -1;
	    if (len == 1) return l >= 0 ? l : -1;
	    if (len == 2) {
	        if (a[l] >= 0) return l;
	        if (a[r] >= 0) return r;
	        return -1;
	    }
	    // len > 2
	    int m = (l + r) / 2;
	    if (a[m] >= 0) return firstPositive(a, l, m);
	    return firstPositive(a, m, r);
	} 
	
	// works on distinct elements
	private static int magic(int[] arr, int start,
            int end) {
        int size = end - start + 1;
        if (size < 0) return -1;
        if (size == 1) {
	        if (arr[start] == start) return start;
	        return -1;        
        }
        if (size == 2) {
	        if (arr[start] == start) return start;
	        else if (arr[end] == end) return end;
	        else return -1;
        }
        // size > 2
        if (arr[start] >= 0) {
	        if (arr[start] == start) return start;
	        return -1;
        }
        // size > 2, arr[start] < 0
        if (arr[end] < 0) return -1;

        // size > 2, arr[start] < 0, arr[end] >= 0
        int m = (start + end) / 2;
        if (arr[m] == m) return m;
        if (arr[m] >= 0) magic(arr, start, m);
        return magic(arr, m, end);
	}
	// size = end - start + 1 > 2
	// => start < fl ((start + end) / 2) < end
	// start < fl ((start + end) / 2) :
	// Have:  start + 1 < end
	// =>   2 start + 2 < start + end
	// =>     start + 1 < (start + end) / 2
    // =>     start + 1 <= fl ((start + end) / 2)
    // =>     start < fl ((start + end) / 2)	
}