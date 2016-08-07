import java.util.Stack;

public class Hanoi {
	static class Node {
		int n;
		int start;
		int end;
		int pivot;
		Node(int n, int start, int end, int pivot) {
			this.n = n;
			this.start = start;
			this.end = end;
			this.pivot = pivot;
		}
		
		Node left() {
			if (n > 1) {
				return new Node(n - 1, start, pivot, end);
			}
			return null;
		}
		
		Node right() {
			if (n > 1) {
				return new Node(n - 1, pivot, end, start);
			}
			return null;
		}
		
		void visit() {
			System.out.println("" + start + " -> " + end);
		}
	}
	
	// Imitates recursive solution, visiting in-order a moves tree
	public static void hanoi(int n) {
		if (n < 1) {
			throw new IllegalArgumentException("n must be >= 1");
		}
		
		Node node = new Node(n, 1, 3, 2);
		Stack<Node> s = new Stack<Node>();
		while (node != null || !s.isEmpty()) {
			if (node == null) {
				node = s.pop();
				node.visit();
				node = node.right();
			} else {
				s.push(node);
				node = node.left();
			}
		}
	}
	
	public static void main(String[] args) {
		hanoi(4);
	/*	1 -> 2
		1 -> 3
		2 -> 3
		1 -> 2
		3 -> 1
		3 -> 2
		1 -> 2
		1 -> 3
		2 -> 3
		2 -> 1
		3 -> 1
		2 -> 3
		1 -> 2
		1 -> 3
		2 -> 3 */
	}
}