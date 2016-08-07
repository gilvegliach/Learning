public class SuccTree {
    public static class Node {
        int n;
        Node left;
        Node right;
        Node parent;
        Node(int n) {
            this.n = n;
        }

        @Override
        public String toString() {
            return "Node{n=" + n + "}";
        }
    }

    public static Node succ(Node node) {
        if (node == null) return null;
        if (node.right == null) {
            while (node.parent != null && node.parent.right == node) {
                node = node.parent;
            }
            return node.parent;
        } else {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
    }

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);

        one.left = two;
        two.parent = one;
        two.right = three;
        three.parent = two;
        three.right = four;
        four.parent = three;
        four.left = five;
        five.parent = four;
        five.left = six;
        five.right = seven;
        six.parent = five;
        seven.parent = five;
        seven.left = eight;
        eight.parent = seven;

        Node node = succ(one);
        System.out.println(node);
        assert node == null;

        node = succ(three);
        System.out.println(node);
        assert node == six;
        
        node = succ(four);
        System.out.println(node);
        assert node == one;
    }
}
