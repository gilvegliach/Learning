import java.util.*;

public class TreePathSum {
    static class Node {
        int n;
        Node left;
        Node right;
        Node(int n) {
            this.n = n;
        }
    }

    static class Path {
        Deque<Node> nodes = new LinkedList<Node>();

        Path() { }

        Path(Node node) {
            nodes.offerFirst(node);
        }

        public boolean addFirst(Node node) {
            return nodes.offerFirst(node);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Iterator<Node> i = nodes.iterator(); i.hasNext(); ) {
                Node node = i.next();
                sb.append(node.n);
                if (i.hasNext()) sb.append(" -> ");
            }
            return sb.toString();
        }
    }

    static void listPaths(Node root, int sum) {
        if (root == null) return;
        List<Path> paths = listPathsFromRoot(root, sum);
        for (Path path : paths) {
            System.out.println(path);
        }
        listPaths(root.left, sum);
        listPaths(root.right, sum);
    }

	// assumes n's are positive
    static List<Path> listPathsFromRoot(Node root, int sum) {
        List<Path> res = new ArrayList<Path>();
        if (root == null) return res;
        if (root.n == sum) {
            res.add(new Path(root));
            return res; 
        }
        res.addAll(listPathsFromRoot(root.left, sum - root.n));
        res.addAll(listPathsFromRoot(root.right, sum - root.n));
        for (Path path : res) {
            path.addFirst(root);
        }
        return res;
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        Node l = new Node(3);
        Node r = new Node(6);
        Node ll = new Node(2);
        Node lr = new Node(5);
        Node rr = new Node(2);
        Node lrl = new Node(2);
        Node rrl = new Node(2);
        root.left = l;
        root.right = r;
        l.left = ll;
        l.right = lr;
        r.right = rr;
        lr.left = lrl;
        rr.left = rrl;
        listPaths(root, 10);
        // 4 -> 6
        // 3 -> 5 -> 2
        // 6 -> 2 -> 2
    }
}
