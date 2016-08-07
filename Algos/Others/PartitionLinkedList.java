public class PartitionLinkedList {
	static class Node {
		int num;
		Node next;
		
		Node(int n) {
			num = n;
		}
	}
	
	static void print(Node head) {
		System.out.print("[");
		while (head != null) {
			System.out.print(head.num);
			head = head.next;
			if (head != null) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}
	
	static Node partition(Node head, int n) {
		if (head == null) return null;
		
		Node leftHead = null, leftLast = null,
			rightHead = null, rightLast = null;
		while (head != null) {
			Node curr = head;
			head = head.next;
			curr.next = null;
						
			if (curr.num < n) {
				if (leftHead == null) {
					leftHead = curr;
					leftLast = curr;
				} else {
					leftLast.next = curr;
					leftLast = leftLast.next;
				}
			} else {
				if (rightHead == null) {
					rightHead = curr;
					rightLast = curr;
				} else {
					rightLast.next = curr;
					rightLast = rightLast.next;
				}
			}
		}
		
		if (leftHead == null) {
			return rightHead;
		}
		
		leftLast.next = rightHead;
		return leftHead;
	}
	
	public static void main(String[] args) {
		// 1 6 3 2 1 7 | 3
		// Output: 1 1 2 | 3 6 7 
		Node v = new Node(1), list = v;
		v.next = new Node(6);
		v = v.next;
		v.next = new Node(3);
		v = v.next;
		v.next = new Node(2);
		v = v.next;
		v.next = new Node(1);
		v = v.next;
		v.next = new Node(7);
		
		print(list);
		partition(list, 3);
		print(list);
	}
}