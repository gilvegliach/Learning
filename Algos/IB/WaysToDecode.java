// A message containing letters from A-Z is being encoded to numbers 
// using the following mapping:
// 
// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// Given an encoded message containing digits, determine the total number
// of ways to decode it.
// 
// Example :
// 
// Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
// 
// The number of ways decoding "12" is 2.
//
public class WaysToDecode {
  public int numDecodings(String a) {
      return numDec2(a);
  }
  
  int numDec2(String s) {
      int n = s.length();
      if (n == 0) return 1;
      int prevPrev = 1;
      int prev = s.charAt(n - 1) == '0' ? 0 : 1;
      for (int i = n - 2; i >= 0; i--) {
          int curr = 0;
          char c = s.charAt(i);
          if (c != '0') {
              curr = prev;
              int d = s.charAt(i + 1);
              int k = (c - '0') * 10 + (d - '0');
              if (k <= 26) curr += prevPrev; 
          }
          prevPrev = prev;
          prev = curr;
      }
      return prev;
  }
  
  int numDecRec(String s, HashMap<String, Integer> cache) {
      int n = s.length();
      if (n == 0) return 1;
      if (s.charAt(0) == '0') return 0;
      if (n == 1) return 1;
      if (cache.containsKey(s)) return cache.get(s);
      
      int res = numDecRec(s.substring(1), cache);
      char d = s.charAt(0);
      char u = s.charAt(1);
      int k = (d - '0') * 10 + (u - '0');
      if (k <= 26) res += numDecRec(s.substring(2), cache);
      cache.put(s, res);
      return res;
  }
}
