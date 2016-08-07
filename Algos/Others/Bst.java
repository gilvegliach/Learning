import java.util.Queue;
import java.util.LinkedList;

public class Bst {
    public static void main(String[] args) {
        Node root = new Node(4);
        Node v = new Node(2);
        insert(root, v);
        insert(root, new Node(1));
        insert(root, new Node(3));
        insert(root, new Node(5));
        insert(root, new Node(6));
        print(root);
        System.out.println();
        delete(root, v);
        print(root);
    }
        
        
static class Node {
	int elem;
	Node parent;
	Node left;
	Node right;
	Node(int e) {
		elem = e;
	}
}

static Node delete(Node root, Node v) {
	if (v == null) return root;
    if (v.left == null && v.right == null) {
		if (v.parent.left == v) {
			v.parent.left = null;
		} else {
			v.parent.right = null;
		}
		v.parent = null;
        return root;
    }
    if (v.left != null && v.right == null) {
        return replace(root, v, v.left);
    } else if (v.right != null && v.left == null) {
	    return replace(root, v, v.right);
    }
    Node succ = min(v.right);
    // succ != null
    if (succ == v.right) {
        Node vr = v.right;
	    replace(root, v, vr);
	    vr.left = v.left;
    } else {
	    // succ.left == null
	    replace(root, succ, succ.right);
	    succ.right = null;
	    succ.parent = v.parent;
	    succ.left = v.left;
	    succ.right = v.right;
	    v.parent = null;
	    v.left = null;
	    v.right = null;
    }
    return root;
}

static Node min(Node v) {
	if (v == null) return null;
	while (v.left != null) {
		v = v.left;
	}
	return v;
}

static void print(Node root) {
	if (root == null) {
		System.out.println();
		return;
    }
    int h = height(root);
	Queue<Node> curr = new LinkedList<Node>();
	Queue<Node> next = new LinkedList<Node>();
	curr.offer(root);
    while (h >= 0) {
        printSpaces((1 << h + 1) - 1);	
        while (!curr.isEmpty()) {
        	Node v = curr.poll();
	        System.out.print(v == null ? " " : v.elem);
	        printSpaces((1 << h + 2) - 1);
	        next.offer(v != null ? v.left : null);
	        next.offer(v != null ? v.right : null);
        }
        System.out.println();
        Queue<Node> temp = curr;
        curr = next;
        next = temp; // empty
        h--;
    }
}

static void printSpaces(int n) {
	for (int i = 0; i < n; i++) {
		System.out.print(" ");
	}
}

static int height(Node root) {
	if (root == null) return -1;
	return 1 + Math.max(height(root.left),
		height(root.right));
}


/** Replaces subtree u of root by tree v */
static Node replace(Node root, Node u, Node v) {
	if (root == u) {
		// forces v to be the root
        v.parent = null; 
        return v;
    }
	// u.parent != null
	if (u.parent.left == u) {
		u.parent.left = v;
	} else {
		u.parent.right = v;
	}
	v.parent = u.parent;
	u.parent = null;
	// u completely unlinked from root
	return root;
}


static Node insert(Node root, Node node) {
	if (root == null) return node;
	if (node == null) return root;
	Node p = root;
	Node curr = (node.elem <= p.elem)
? p.left 
: p.right;
	while (curr != null) {
		p = curr;
		curr = (node.elem <= curr.elem)
				? curr.left
				: curr.right;
	}
	if (node.elem <= p.elem) {
		p.left = node;
	} else {
		p.right = node;
	}
	node.parent = p;
	return root;
}

}