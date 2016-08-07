import java.util.*;

public class Skyline {
  public static void main(String[] args) {
    //ArrayList<Segment> skyline = new ArrayList<Segment>() {{
    //  add(new Segment(1, 3, 2));
    //  add(new Segment(3, 6, 3));
    //  add(new Segment(6, 7, 1));
    //  add(new Segment(7, 9, 5));
    //  add(new Segment(9, 11, 3));
    //}};
    ArrayList<Segment> segs = new ArrayList<Segment>() {{
      add(new Segment(2, 4, 2));
      add(new Segment(3, 6, 3));
      add(new Segment(5, 8, 1));
      add(new Segment(7, 17, 2));
      add(new Segment(10, 12, 6));
    }};
    print(skyline(segs));
  }

  static class Segment {
    int start;
    int end;
    int height;
    Segment(int s, int e, int h) {
      start = s;
      end = e;
      height = h;
    }
  }

  static List<Segment> skyline(List<Segment> segs) {
    int n = segs.size();
    if (n == 1) return segs;
    int start = segs.get(0).start;
    int end = segs.get(0).end;
    for (int i = 1; i < n; i++) {
      Segment s = segs.get(i);
      if (s.start < start) start = s.start;
      if (s.end > end) end = s.end;
    }
    Segment init = new Segment(start, end, 0);
    ArrayList<Segment> res = new ArrayList<Segment>() {{
      add(init);
    }};
    for (Segment s : segs) {
      res = merge(res, s);
    }
    return res;
  }

  static ArrayList<Segment> merge(List<Segment> segs, Segment seg) {
    segs = split(segs, seg.start);
    segs = split(segs, seg.end);
    ArrayList<Segment> merged = new ArrayList<Segment>();
    for (Segment s : segs) {
      if (seg.start <= s.start && s.end <= seg.end) {
        s.height = Math.max(s.height, seg.height);
      }
      merged.add(s);
    }
    int n = segs.size();
    if (n == 1) return merged;

    ArrayList<Segment> res = new ArrayList<Segment>();
    Segment s = segs.get(0);
    for (int i = 1; i < n; i++) {
      while (i < n && segs.get(i).height == s.height) i++;
      if (i == n) break;
      res.add(new Segment(s.start, segs.get(i - 1).end, s.height));
      s = segs.get(i);
    }
    res.add(new Segment(s.start, segs.get(n - 1).end, s.height));
    return res;
  }

  static ArrayList<Segment> split(List<Segment> segs, int x) {
    ArrayList<Segment> res = new ArrayList<Segment>(segs.size() + 1);
    for (Segment seg : segs) {
      if (seg.start < x && x < seg.end) {
        res.add(new Segment(seg.start, x, seg.height));
        res.add(new Segment(x, seg.end, seg.height));
      } else {
        res.add(seg);
      }
    }
    return res;
  }

  static void print(List<Segment> segments) {
    int n = segments.size();
    if (n == 0) return;
    int cols = 0;
    for (Segment segment : segments) {
      if (segment.height > cols) cols = segment.height;
    }
    Segment first = segments.get(0);
    Segment last = segments.get(n - 1);
    int start = first.start;
    int end = last.end;
    int rows = end - start + 1;
    boolean[][] m = new boolean[rows][cols];
    for (Segment segment : segments) {
      for (int i = segment.start - start; i < segment.end - start; i++) {
        for (int k = 0; k < segment.height; k++) {
          m[i][k] = true;
        }
      }
    }
    for (int j = cols - 1; j >= 0; j--) {
      for (int i = 0; i < rows; i++) {
        System.out.print(m[i][j] ? "x" : " ");
      }
      System.out.println();
    }
  }
}

