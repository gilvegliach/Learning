import java.util.*;
import java.util.stream.*;

public class AnagramWithJ8Merge {
  public static void main(String[] args) {
    String s = "abbcc";
    String t = "bcacb";
    String r = "Xbcacb";
    System.out.println(isAnagram(s, t));
    System.out.println(isAnagram(s, r));
  }

  static boolean isAnagram(String s, String t) {
    Map<Character, Integer> hist = new HashMap<>();
    s.chars().forEach(c -> hist.merge((char) c, 1, Integer::sum)); // chars() is a stream of ints
    t.chars().forEach(c -> hist.merge((char) c, -1, Integer::sum));
    return hist.values().stream().allMatch(v -> v == 0);
  }
}
