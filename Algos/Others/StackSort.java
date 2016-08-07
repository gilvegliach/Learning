import java.util.Stack;

public class StackSort {
	public static <T extends Comparable<T>> void sort(Stack<T> stack) {
		if (stack == null || stack.isEmpty()) return;
		
		Stack<T> left = stack;
		Stack<T> right = new Stack<T>();
		while (!left.isEmpty()) {
			T temp = left.pop();
			while (!right.isEmpty() && temp.compareTo(right.peek()) > 0) {
				left.push(right.pop());
			}
			right.push(temp);
		}
		while (!right.isEmpty()) {
			left.push(right.pop());
		}
	}
	
	public static <T> void print(Stack<T> stack) {
		System.out.print("[");
		int size = stack.size();
		for (int i = 0; i < size; i++) {
			System.out.print(stack.get(i));
			if (i < size - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}
	
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(5);
		s.push(1);
		s.push(3);
		s.push(4);
		s.push(2);
		
		print(s);
		sort(s);
		print(s);
	}
}