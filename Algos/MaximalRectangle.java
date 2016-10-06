// Given a 2D binary matrix filled with 0’s and 1’s, find the largest rectangle 
// containing all ones and return its area.
// 
// Bonus if you can solve it in O(n^2) or less.

import java.util.*;

public class MaximalRectangle {
    static class P { int i; int v; P(int ii, int vv) { i = ii; v = vv; } }
    
	public int maximalRectangle(ArrayList<ArrayList<Integer>> a) {
	    int n = a.size();
	    int m = a.get(0).size();
	    int res = a.get(0).get(0);
	    int[][] board = new int[n][m];
	    for (int i = 0; i < n; i++) {
	        for (int j = m - 1; j >= 0; j--) {
	            int elem = a.get(i).get(j);
	            if (elem != 0) {
	                board[i][j] = j < m - 1 ? board[i][j + 1] + 1 : 1;
	            }
	        }
	    }
	    
	   // O(m * n ^ 2)
	   // for (int i = 0; i < n; i++) {
	   //     for (int j = 0; j < m; j++) {
	   //         int elem = a.get(i).get(j);
	   //         if (elem == 0) continue;
	   //         int w = Integer.MAX_VALUE;
	   //         for (int k = i; k < n; k++) {
	   //             if (board[k][j] == 0) break;
	   //             w = Math.min(w, board[k][j]);
	   //             res = Math.max(res, (k - i + 1) * w);
	   //         }
	   //     }
	   // }
	   
	   // O(mn)
	   for (int j = 0; j < m; j++) {
	       Stack<P> s = new Stack<P>();
	       int max = -1;
	       for (int i = 0; i < n; i++) {
	            int elem = board[i][j];
	            while (!s.isEmpty() && elem <= s.peek().v) {
	                P p = s.pop();
	                int w = i - 1 - (s.isEmpty() ? -1 : s.peek().i);
	                max = Math.max(max, p.v * w);
	            }
	            s.push(new P(i, elem));
	       }
	       while (!s.isEmpty()) {
	           P p = s.pop();
	           int w = n - 1 - (s.isEmpty() ? -1 : s.peek().i);
	           max = Math.max(max, p.v * w);
	       }
	       res = Math.max(res, max);
	           
	   }
	   return res;
	}
}