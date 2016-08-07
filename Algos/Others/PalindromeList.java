public class PalindromeList {
	static class Node {
		int n;
		Node next;
		Node(int n) {
			this.n = n;
		}
	}
	
	static boolean isPalindrome(Node head) {
		if (head == null) {
			throw new IllegalArgumentException("head cannot be null");
		}
		if (head.next == null) {
			return true;
		}
		
		Node prev = null;
		Node curr = head;
		Node fast = head.next;
		Node tail = null;
		while (true) {
			Node next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
			fast = fast.next;
			if (fast == null) {
				tail = curr;
				break;
			}
			
			fast = fast.next;
			if (fast == null) {
				tail = curr.next;
				break;
			}
		}
		
		boolean isPalindrome = true;
		while (tail != null) {
			if (isPalindrome && tail.n != prev.n) {
				isPalindrome = false;
			}
			Node next = prev.next;
			prev.next = curr;
			curr = prev;
			prev = next;
			
			tail = tail.next;
		}
		return isPalindrome;
	}

	static void print(Node head) {
		System.out.print("[");
		while (head != null) {
			System.out.print(head.n);
			if (head.next != null) {
				System.out.print(", ");
			}
			head = head.next;
		}
		System.out.println("]");
	}
	
	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(2);
		print(head);
		System.out.println(isPalindrome(head)); // false
		print(head); // should be unchanged
		
		head.next.next = new Node(1);
		print(head);
		System.out.println(isPalindrome(head)); // true
		print(head);
		
		head.next.next = new Node(3);
		head.next.next.next = new Node(2);
		print(head);
		System.out.println(isPalindrome(head)); // false
		
		head.next.next.next.next = new Node(1);
		print(head);
		System.out.println(isPalindrome(head)); // true
	}
}