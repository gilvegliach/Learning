import java.util.HashSet;

public class CycleDetection {
	static class Node {
		char c;
		Node next;
		Node(char c) {
			this.c = c;
		}
	}
	
	static Node addHead(Node head, Node tail) {
		if (head == null) throw new IllegalArgumentException();
		head.next = tail;
		return head;
	}
	
	static void print(Node head) {
		System.out.print("[");
		while (head != null) {
			System.out.print(head.c);
			head = head.next;
			if (head != null) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}
	
	static Node checkCycle(Node head) {
		if (head == null) return null;
		HashSet<Node> dup = new HashSet<Node>();
		while (head != null) {
			if (dup.contains(head)) return head;
			dup.add(head);
			head = head.next;
		}
		return null;
	}
	
	// More memory efficient algorithm: the set stores only the 
	// nodes in the cycle. This is better if the cycle is small
	// and at the very end of a long list
	static Node checkCycle2(Node head) {
		if (head == null) return null;
		Node curr = head;
		Node runner = curr.next;
		if (runner == null) {
			return null;
		}
		if (runner == curr) {
			return curr;
		}
		runner = runner.next;
		
		// Points at a Node in the cycle
		Node cycle = null;
		
		// Runner could be null or point at first or second node
		while (runner != null) {
			if (runner == curr) {
				cycle = curr;
				break;
			} 
			runner = runner.next;
			if (runner == null) {
				break;
			}
			if (runner == curr) {
				cycle = curr;
				break;
			}
			runner = runner.next;
			curr = curr.next;
		}
		// No cycle found
		if (cycle == null) return null;
		
		HashSet<Node> cycleNodes = new HashSet<Node>();
		cycleNodes.add(cycle);
		curr = cycle.next;
		while (curr != cycle) {
			cycleNodes.add(curr);
			curr = curr.next;
		}
		
		curr = head;
		while (!cycleNodes.contains(curr)) {
			curr = curr.next;
		}
		return curr;
	}
	
	// Floyd's algo (tortoise and hare)
	static Node checkCycle3(Node head) {
		if (head == null) return null;
		
		Node slow = head, fast = head;
		do {
			slow = slow.next;
			fast = fast.next;
			if (fast == null) return null;
			fast = fast.next;
		} while (slow != fast);
		
		while (head != slow) {
			head = head.next;
			slow = slow.next;
		}
		return head;
	}
	
	public static void main(String[] args) {
		Node last = new Node('e');
		Node c = new Node('c');
		
		Node tail = addHead(new Node('d'), last);
		tail = addHead(c, tail);
		tail = addHead(new Node('b'), tail);
		Node head = addHead(new Node('a'), tail);
		print(head);
		
		last.next = c;
		Node v = checkCycle3(head);
		if (v != null) {
			System.out.println("Cycle at: " + v.c);
		} else {
			System.out.println("No cycles");
		}
	}
}