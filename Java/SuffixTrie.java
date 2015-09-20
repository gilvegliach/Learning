import java.util.*;

public class SuffixTrie {
    static class Node {
        char c; // incoming edge
        List<Node> children = new ArrayList<>();

        Node(char c) {
            this.c = c;
        }

        Node addChild(char c) {
            Node n = new Node(c);
            children.add(n);
            return n;
        }

        Node getChild(char c) {
            for (Node child : children) {
                if (child.c == c) {
                    return child;
                }
            }
            return null;
        }

        boolean hasChildren() {
            return !children.isEmpty();
        }

        List<Node> getChildren() {
            return children;
        }
    }

    public static void main(String[] args) {
        String s = "abaaba";
        String t = "aba";
        Node trie = build(s);
        boolean res = hasSubstring(trie, t);
        System.out.println(res);
        System.out.println(stringify(trie));
        assert res;
    }

    static Node build(String s) {
        Node root = new Node('-'); // no incoming edges in root
        root.addChild('$');
        for (int i = 0; i < s.length(); i++) {
            String t = s.substring(i);
            Node curr = root;
            for (int j = 0; j < t.length(); j++) {
                char c = t.charAt(j);
                Node child = curr.getChild(c);
                if (child != null) {
                    curr = child;
                } else {
                    curr = curr.addChild(c);
                }
            }
            curr.addChild('$');
        }
        return root;
    }

    static boolean hasSubstring(Node root, String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Node child = root.getChild(c);
            if (child == null) return false;
            root = child;
        }
        return true;
    }

    static String stringify(Node root) {
        if (!root.hasChildren()) {
            return "(" + root.c + ")";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(root.c);
        List<Node> children = root.getChildren();
        for (int i = 0; i < children.size(); i++) {
            sb.append(" ");
            sb.append(stringify(children.get(i)));
        }
        sb.append(")");
        return sb.toString();
    }    
}            
