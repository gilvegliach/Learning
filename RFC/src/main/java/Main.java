import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.generate;

public class Main {
  public static void main(String[] args) {
    String s = "the quick brown fox ju".replaceAll(" ", "");
    String cypherText = encrypt(s, 4);
    String clearText = decrypt(cypherText, 4);
    System.out.println(cypherText);
    System.out.println(clearText);
  }

  static String encrypt(String s, int r) {
    List<StringBuilder> sbs = generate(StringBuilder::new).limit(r).collect(toList());
    int n = s.length();
    int j = 0;
    int dir = -1;
    for (int i = 0; i < n; i++) {
      sbs.get(j).append(s.charAt(i));
      if (j == 0 || j == r - 1) dir = -dir;
      j += dir;
    }
    return sbs.stream().collect(Collectors.joining());
  }

  // assumes to simplify math: r - 1 divides n
  static String decrypt2(String s, int r) {
    int n = s.length();
    int rr = r - 1;
    int q = n / rr;
    StringBuilder sb = new StringBuilder(n);
    for (int t = 0; t < n; t++) {
      int i;
      int j = t / rr;
      if (j % 2 == 0) {
        i = t % rr;
      } else {
        i = rr - (t % rr);
      }
      if (i == 0) j = t / rr / 2;
      if (i == rr) j = (t - rr) / rr / 2;

      int w = j;
      if (i > 0) w += (q - q / 2) + (i - 1) * q;
      sb.append(s.charAt(w));
    }
    return sb.toString();
  }

  static String decrypt(String s, int r) {
    List<List<Integer>> iss = generate(ArrayList<Integer>::new).limit(r).collect(toList());
    int n = s.length();
    int j = 0;
    int dir = -1;
    for (int i = 0; i < n; i++) {
      iss.get(j).add(i);
      if (j == 0 || j == r - 1) dir = -dir;
      j += dir;
    }

    List<Integer> is = new ArrayList<>();
    for (int i = 0; i < r; i++) is.addAll(iss.get(i));

    char[] cs = new char[n];
    for (int i = 0; i < n; i++) cs[is.get(i)] = s.charAt(i);
    return new String(cs);
  }
}


/*
x.....x.....x
.x...x.x...x.
..x.x...x.x..
...x.....x...
0123456789012

r = 4
n = 13
rr = 3

t.....c.....  n..
.h...i.k...w  .f.
..e.u...b.o.  ..o
...q.....r..  ...
012345678901  234

tcn hikw eubo qr

tchikweuboqr
012345678901

# elems per row
rr = r - 1
first row: ceil(n / rr / 2)
last row: ceil((n - rr) / rr / 2)
row: floor(n / rr) + [i >= n % rr]

given t
find i, j

output w

assume rr | n:
q = fl(n / rr)
if (i == 0)
  w = j
else
  w = fl(q / 2) + (i - 1) * q + j
 */





