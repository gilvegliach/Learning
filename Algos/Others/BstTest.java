public class BstTest {
    static class Node {
        Node left;
        Node right;
        int n;
        Node(int n) {
            this.n = n;
        }
    }
    
    public static boolean isBst(Node root) {
        return isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    static boolean isBst(Node root, int left, int right) {
        if (root == null) return true;
        if (root.n < left || root.n > right) return false;
        return isBst(root.left, left, root.n) && isBst(root.right, root.n + 1, right);
    }

    public static void main(String[] args) {
        Node one = new Node(1);
        Node four = new Node(4);
        Node three = new Node(3);
        three.left = one;
        three.right = four;

        Node seven = new Node(7);
        Node thirteen = new Node(13);
        thirteen.left = seven;

        Node five = new Node(5);
        five.left = three;
        five.right = thirteen;
        
        boolean result = isBst(five);
        System.out.println(result);
        assert result == true;

        thirteen.n = 6;
        result = isBst(five);
        System.out.println(result);
        assert result == false;
    }
}
