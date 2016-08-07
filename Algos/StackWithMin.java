import java.util.*;

// Stack with min in O(1) time and space.
// Min is always the real min of the stack.
// If the pushed elem is < curr min, the new
// min is the elem and we push a combo of
// elem and curr min.
// From this number and the new min, we can 
// derive the previous min. 
public class StackWithMin {
  private int min;
  private final Stack<Integer> stack = new Stack<Integer>();

  public void push(int n) {
    if (stack.isEmpty()) {
      min = n;
      stack.push(n);
      return;
    }
    if (n >= min) {
      stack.push(n);
      return;
    }
    stack.push(2 * n - min);
    min = n;  
  }

  public int pop() {
    if (stack.isEmpty()) throw new EmptyStackException();
    int n = stack.pop();
    if (n >= min) return n;
    int t = min;
    min = 2 * min - n; // prev min
    return t;
  }

  public boolean isEmpty() {
    return stack.isEmpty();
  }

  public int min() {
    if (stack.isEmpty()) throw new EmptyStackException();
    return min;
  }
  
  public static void main(String[] args) {
    int[] a = { 2, -4, 3, 5, 1, -9, -10 };
    StackWithMin s = new StackWithMin();
    for (int n : a) {
      s.push(n);
      System.out.println("Push: " + n + ", min: " + s.min());
    }
    System.out.println();
    while (!s.isEmpty()) {
      System.out.println("Pop: " + s.pop());
    }
  }
}
