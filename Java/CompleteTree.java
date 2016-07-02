import static java.lang.Math.max;

public class CompleteTree {
  static class R {
    boolean isComplete; int n;
    R(int nn, boolean compl) {
      n = nn; isComplete = compl;
    }
  }

  static class Node {
    int n;
    Node left; Node right;
    Node(int nn) { n = nn; }
  }

  static int maxCompleteTreeSize(Node root) {
    return _maxCompleteTreeSize(root).n;
  }

  public static void main(String[] args) {
    Node one = new Node(1);
    Node two = new Node(2);
    Node three = new Node(3);
    Node four = new Node(4);
    Node five = new Node(5);
    one.left = two; //one.right = three;
    two.left = four; two.right = five;
    System.out.println(maxCompleteTreeSize(one));
  }

  static R _maxCompleteTreeSize(Node root) {
    if (root == null) return new R(0, true);

    R rl = _maxCompleteTreeSize(root.left);
    R rr = _maxCompleteTreeSize(root.right);

    if (!rl.isComplete || !rr.isComplete) {
      return new R(max(rl.n, rr.n), false);
    }
    int hl = height(rl.n);
    int hr = height(rr.n);
    if ((hl == hr && isPerfect(rl.n))
        || (hl == hr + 1 && isPerfect(rr.n))) {
        return new R(rl.n + rr.n + 1, true);
    } else {
        return new R(max(rl.n, rr.n), false);
    } 
  }

  static int height(int n) {
    // 2^h <= n < 2^{h + 1} 
    int h = 0; int k = 1;
    while (k <= n) { h++; k <<= 1; } 
    return h - 1;
  }

  static boolean isPerfect(int n) {
    // n is of form 2^k - 1
    return (n + 1 & n)== 0;
  }
}
