public class DeleteElementList {
	static class Node {
		int n;
		Node next;
		Node(int n) {
			this.n = n;
		}
	}
	
	public static void print(Node head) {
		System.out.print("[");
		while (head != null) {
			System.out.print(head.n);
			head = head.next;
			if (head != null) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}
	
	// Instead of looping through the rest of the list, this can be done better
	// deleing only the next node and link node.next to node.next.next
	public static void delete(Node node) {
		if (node == null) return;
		if (node.next == null) {
			throw new IllegalArgumentException("can't delete last node");
		}
		// Post-cond: node, node.next != null		
		Node prev = node;
		Node curr = node.next;
		prev.n = curr.n;
		
		while (curr.next != null) {
			prev = curr;
			curr = curr.next;
			prev.n = curr.n;
		}
		prev.next = null;
	}
	
	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		print(head);
		delete(head.next); // del 2
		print(head);
		delete(head.next); // del 3
		print(head);
	}
}