//ctci 18.10
import java.util.*;

public class WordWalk {
    static boolean isDistOne(String s, String t) {
        int lens = s.length();
        int lent = t.length();
        if (lens != lent) return false;
        HashSet<Character> set = new HashSet<>(lens);
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        int dst = lent;
        for (int i = 0; i < lent; i++) {
            char c = t.charAt(i);
            if (set.contains(c)) dst--;
        }
        return dst == 1;
    }

    static class Node {
        final String s;
        final List<Node> adj = new ArrayList<>();
        Node prev;
        boolean visited;

        Node(String ss) {
            s = ss;
        }
    }

    static List<Node> buildGraph(List<String> words) {
        int size = words.size();
        List<Node> nodes = new ArrayList<>(size);
        for (String word : words) {
            nodes.add(new Node(word));
        }
        for (int i = 0; i < size; i++) {
            Node v = nodes.get(i);
            for (int j = i + 1; j < size; j++) {
                Node u = nodes.get(j);
                if (isDistOne(v.s, u.s)) {
                    v.adj.add(u);
                }
            }
        }
        return nodes;
    }
    
    static Node bfs(Node src, String target) {
        Queue<Node> q = new LinkedList<>();
        src.visited = true;
        q.offer(src);
        while (!q.isEmpty()) {
            Node v = q.poll();
            for (Node u : v.adj) {
                if (!u.visited) {
                    u.prev = v;
                    if (target.equals(u.s)) {
                        return u;
                    }
                    q.offer(u);
                }
            }
            v.visited = true;    
        }
        return null;
    }

    static void printPath(String s, String t, List<String> words) {
        words.add(0, s);
        words.add(t);
        Node src = buildGraph(words).get(0);
        Node dst = bfs(src, t);
        if (dst == null) {
            System.out.println(s + " cannot be transformed to " + t);
            return;
        }
        Stack<String> stack = new Stack<>();
        while (dst != null) {
            stack.push(dst.s);
            dst = dst.prev;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
            if (!stack.isEmpty()) System.out.print(" -> ");
        }
        System.out.println();
    }    
    
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("lamp");
        words.add("limp");
        words.add("lime");
        printPath("damp", "like", words);
    }
}
