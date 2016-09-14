// Given a sorted array of integers, find the starting and ending position of a given target value.
// Your algorithm’s runtime complexity must be in the order of O(log n).
// If the target is not found in the array, return [-1, -1].

public class SearchRange {
	// Example:
    // Given [5, 7, 7, 8, 8, 10]
    // and target value 8,
    // return [3, 4].
	// DO NOT MODIFY THE LIST
	public ArrayList<Integer> searchRange(final List<Integer> a, int b) {
	    ArrayList<Integer> result = new ArrayList<>(2);
	    int k = a.size() - 1;
	    result.add(findLower(a, b, 0, k));
	    result.add(findUpper(a, b, 0, k));
	    return result;
	}
	
	int findLower(List<Integer> arr, int target, int l, int r) {
	    if (l > r) return -1;
	    if (l == r) {
	        int elem = arr.get(l);
	        return elem == target ? l : -1;
	    }
	    int m = l + (r - l) / 2;
	    int elem = arr.get(m);
	    if (target > elem) {
	        return findLower(arr, target, m + 1, r);
	    } else if (target < elem) {
	        return findLower(arr, target, l, m - 1);
	    }
	    return findLower(arr, target, l, m);
	}
	
	int findUpper(List<Integer> arr, int target, int l, int r) {
	    if (l > r) return -1;
	    if (l == r) {
	        int elem = arr.get(l);
	        return elem == target ? l : -1;
	    }
	    if (r - l == 1) {
	        int elem = arr.get(r);
	        if (elem == target) return r;
	        elem = arr.get(l);
	        if (elem == target) return l;
	        return -1;
	    }
	    int m = l + (r - l) / 2;
	    int elem = arr.get(m);
	    if (target < elem) {
	        return findUpper(arr, target, l, m - 1);
	    } else if (target > elem) {
	        return findUpper(arr, target, m + 1, r);
	    }
	    return findUpper(arr, target, m, r);
	}
}