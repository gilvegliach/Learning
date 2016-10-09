// Given an array of non-negative integers, you are initially positioned 
// at the first index of the array.
// 
// Each element in the array represents your maximum jump length at that
// position.
// 
// Determine if you are able to reach the last index.
// 
// For example:
// A = [2,3,1,1,4], return 1 ( true ).
// 
// A = [3,2,1,0,4], return 0 ( false ).
// 
// Return 0/1 for this problem
// 
public class Solution {
  public int canJump(ArrayList<Integer> a) {
      return canJump4(a);
  }
  
  int canJump4(ArrayList<Integer> a) {
      int n = a.size();
      int index = n - 1;
      for (int i = n - 2; i >= 0; i--) {
          int elem = a.get(i);
          if (index <= i + elem) index = i;
      }
      return index == 0 ? 1 : 0;
  }
  
  int canJump3(ArrayList<Integer> a) {
      int n = a.size();
      int[] table = new int[n];
      table[n - 1] = 1;
      for (int i = n - 2; i >= 0; i--) {
          int elem = a.get(i);
          for (int j = i + 1; j <= i + elem; j++) {
              if (table[j] == 1) {
                  table[i] = 1;
                  break;
              }
          }
      }
      return table[0];
  }
  
  int canJump2(ArrayList<Integer> a) {
      int n = a.size();
      int[] table = new int[n];
      table[0] = 1;
      for (int i = 0; i < n; i++) {
          if (table[i] == 1) {
              int bound = Math.min(i + a.get(i),  n - 1);
              for (int j = i + 1; j <= bound; j++) {
                  table[j] = 1;
              }
          }
      }
      return table[n - 1];
  }
  
  int canJumpRec(ArrayList<Integer> a, int i, HashMap<Integer, Integer> cache) {
      if (i == 0) return 1;
      if (cache.containsKey(i)) return cache.get(i);
      for (int j = i - 1; j >= 0 ; j--) {
          int elem = a.get(j);
          if (i - j <= elem) {
              if (canJumpRec(a, j, cache) == 1) {
                  cache.put(i, 1);
                  return 1;
              }
          }
      }
      cache.put(i, 0);
      return 0;
  }
}
