public class SumLinkedList{ 
	static class Node {
		int n;
		Node next;
		Node(int n) {
			this.n = n;
		}
	}
	
	static void print(Node head) {
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
	
	static Node sum(Node left, Node right) {
		int carry = 0;
		Node res = null;
		Node head = null;
		while (left != null || right != null) {
			int sum = carry;
			if (left != null) {
				sum += left.n;
				left = left.next;
			}
			if (right != null) {
				sum += right.n;
				right = right.next;
			}
			carry = sum / 10;
			sum %= 10;
			if (res == null) {
				res = new Node(sum);
				head = res;
			} else {
				res.next = new Node(sum);
				res = res.next;
			}
		}
		
		if (carry > 0) {
			res.next = new Node(carry);
		}
		return head;
	}
	
	static Node reverse(Node head) {
		Node curr = head;
		Node res = null;
		while (curr != null) {
			Node v = curr;
			curr = curr.next;
			v.next = res;
			res = v;
		}
		return res;
	}
	
	static Node sum2(Node left, Node right) {
		return reverse(sum(reverse(left), 
			reverse(right)));
	}
	
	public static void main(String[] args) {
		Node left = new Node(2);
		Node v = left;
		v.next = new Node(9);
		v = v.next;
		v.next = new Node(1);
		
		Node right = new Node(8);
		v = right;
		v.next = new Node(3);
		
		print(left);
		print(right);
		System.out.print("sum rev : ");
		print(sum(left, right));  // 192 + 38 = 230
		System.out.print("sum : ");
		print(sum2(right, left)); // 83 + 291 = 374
	}
}