// ctci 17.13
public class BstToList {
    static class Node {
        Node first, second;
        int val;
        Node(int v) { val = v; }
    }

    static void printForward(Node head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.first != null) System.out.print(" -> ");
            head = head.first;
        }
        System.out.println();
    }
    
    static void printBackward(Node tail) {
        while (tail != null) {
            System.out.print(tail.val);
            if (tail.second != null) System.out.print(" -> ");
            tail = tail.second;
        }
        System.out.println();
    }

    static class Result {
        Node head;
        Node tail;
        Result(Node h, Node t) {
            head = h;
            tail = t;
        }
    }

    static Result convert(Node root) {
        if (root == null) return new Result(null, null);
        Result rl = convert(root.first);
        Result rr = convert(root.second);
        Result res = new Result(root, root);
        if (rl.tail != null) {
            rl.tail.first = root;
            root.second = rl.tail;
            res.head = rl.head;
        }
        if (rr.head != null) {
            rr.head.second = root;
            root.first = rr.head;
            res.tail = rr.tail;
        }
        return res;
    }

    static void printTree(Node root, int depth) {
        if (root == null) return;
        for (int i = 0; i < depth; i++) {
            System.out.print(" ");
        }
        System.out.println(root.val);
        printTree(root.first, depth + 1);
        printTree(root.second, depth + 1);
    }

    public static void main(String[] args) {
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        
        // bst
        five.first = three;
        three.first = two;
        three.second = four;
        five.second = seven;
        seven.first = six;
        seven.second = eight;
        printTree(five, 0);

        Result res = convert(five);
        printForward(res.head);
        printBackward(res.tail);
    }   
}
