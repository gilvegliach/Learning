// Solves 4.9: inputs must not be null, subtree does not have to
// be identical but only contained
public class Subtree {
    public static void main(String[] args) {
        Node small = new Node(1);
        small.left = new Node(2);
        small.right = new Node(3);

        Node big = new Node(5);
        big.left = new Node(6);
        big.left.left = new Node(7);
        big.left.right = new Node(8);
        big.right = small;

        boolean res = hasAsSubtree(big, small);
        System.out.println(res);
        assert res;
    }

    static boolean hasAsSubtree(Node big, Node small) {
        if (big == null) return false;
        return matches(big, small)
            || hasAsSubtree(big.left, small)
            || hasAsSubtree(big.right, small);
    }

    static boolean matches(Node big, Node small) {
        if (small == null) return true;
        return small.n == big.n
            && matches(big.left, small.left)
            && matches(big.right, small.right);
    }

    static class Node {
        int n;
        Node left;
        Node right;
        Node(int n) {
            this.n = n;
        }
    }
}
