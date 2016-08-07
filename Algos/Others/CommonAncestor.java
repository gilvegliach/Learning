import java.util.*;

// CTCI 4.7
public class CommonAncestor {
    static class Node {
        int n;
        Node left;
        Node right;
        Node(int n) {
            this.n = n;
        }

        @Override
        public String toString() {
            return "Node{n=" + n + "}";
        }
    }
   

    enum Found {
        P, Q, BOTH, NONE;
    }

    static class Result {
        Node ancestor = null;
        Found found = Found.NONE;
        Result(Node anc, Found f) {
            ancestor = anc;
            found = f;
        }
    }

    static Result find(Node root, Node p, Node q) {
        if (root == null) return new Result(null, Found.NONE);        
        if (root == p && root == q) return new Result(root, Found.BOTH);

        Result resultLeft = find(root.left, p, q);
        switch (resultLeft.found) {
            case BOTH: { 
                return resultLeft;
            }
            case P: {
                if (root == q) return new Result(root, Found.BOTH);
                Result resultRight = find(root.right, p, q);
                if (resultRight.found == Found.Q) return new Result(root, Found.BOTH);
                return resultLeft;
            }
            case Q: {
                if (root == p) return new Result(root, Found.BOTH);
                Result resultRight = find(root.right, p, q);
                if (resultRight.found == Found.P) return new Result(root, Found.BOTH);
                return resultLeft;
            }
            case NONE: {
                Result resultRight = find(root.right, p, q);
                switch (resultRight.found) {
                    case BOTH: {
                        return resultRight;
                    }
                    case P: {
                        if (root == q) return new Result(root, Found.BOTH);
                        return resultRight;
                    }
                    case Q: {
                        if (root == p) return new Result(root, Found.BOTH);
                        return resultRight;
                    }
                    case NONE: {
                        if (root == p) return new Result(null, Found.P);
                        if (root == q) return new Result(null, Found.Q);
                        return resultRight;
                    }
                } 
            }
        }
        throw new AssertionError("Not reachable");
    }

    public static Node commonAncestor(Node root, Node p, Node q) {
        // root, p, and q must be not null
        return find(root, p, q).ancestor;
    }

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);

        one.left = two;
        two.left = three;
        two.right = four;
        three.left = five;
        three.right = six;
        four.right = seven;
        
        Node result = commonAncestor(one, five, seven);
        System.out.println(result);
        assert result == two;

        result = commonAncestor(one, three, seven);
        System.out.println(result);
        assert result == two;

        result = commonAncestor(one, two, five);
        System.out.println(result);
        assert result == two;

        result = commonAncestor(one, two, seven);
        System.out.println(result);
        assert result == two;

        result = commonAncestor(one, one, six);
        System.out.println(result);
        assert result == one;

        result = commonAncestor(one, six, six);
        System.out.println(result);
        assert result == six;

        result = commonAncestor(one, six, new Node(99));
        System.out.println(result);
        assert result == null;
    }
}
