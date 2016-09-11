/*
You have to paint N boards of length {A0, A1, A2, A3 … AN-1}. 
There are K painters available and you are also given how much time a painter
takes to paint 1 unit of board. You have to get this job done as soon as 
possible under the constraints that any painter will only paint contiguous 
sections of board.

2 painters cannot share a board to paint. That is to say, a board
cannot be painted partially by one painter, and partially by another.
A painter will only paint contiguous boards. Which means a
configuration where painter 1 paints board 1 and 3 but not 2 is
invalid.
Return the ans % 10000003

Input :
K : Number of painters
T : Time taken by painter to paint 1 unit of board
L : A List which will represent length of each board

Output:
     return minimum time to paint all boards % 10000003
Example

Input : 
  K : 2
  T : 5
  L : [1, 10]
Output : 50
*/

import java.util.*;

public class PaintersPartition {
	public static void main(String[] args) {
		ArrayList<Integer> c = new ArrayList<>();
		c.add(658); c.add(786); c.add(531); c.add(47); 
		c.add(169); c.add(397); c.add(914);
		PaintersPartition sol = new PaintersPartition();
		System.out.println(sol.paint(5, 1, c)); // 914
	}
	
    // k = a, t = b
	public int paint(int a, int b, ArrayList<Integer> c) {
	    if (c.isEmpty()) return 0;
	    long start = 0L;
	    long end = sum(c);
	    long lastPossible = end;
	    while (start <= end) {
	        long mid = start + (end - start) / 2;
			// System.out.println("s: " + start +
				// ", m: " + mid +
				// ", e: " + end +
				// ", lp: " + lastPossible);
	        if (isPossible(c, a, mid)) {
	            lastPossible = mid;
	            end = mid - 1;
	        } else {
	            start = mid + 1;
	        }
	    }
	    return (int)((lastPossible * b) % 10000003);
    }
    
    boolean isPossible(ArrayList<Integer> c, int k, long amount) {
        int index = 0;
        int n = c.size();
        long left;
        for (int i = 0; i < k; i++) {
            left = amount;
            while (left - c.get(index) >= 0) {
                left -= c.get(index);
                index++;
                if (index == n) return true;
            }
        }
        return false;
    }
    
    long sum(ArrayList<Integer> c) {
        long sum = 0L;
        for (Integer n : c) {
            sum += n;
        }
        return sum;
    }
}