import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.HashSet;

public class StringUnconcat {
    public static void main(String[] args) {
        //String text = "jesslookedjustliketimherbrother";
        //String[] dict = new String[]{"looked", "just", "like", 
        //    "her", "brother"};
        String text = "abcd";
        String[] dict = new String[]{"a","ab","bc","cd"};
        StringUnconcat su = new StringUnconcat(dict);
        System.out.println(su.parse(text)); 
    }

    private final Dict mDict;
    
    public StringUnconcat(String[] words) {
        mDict = new DictImpl(words);
    }
    
    public String parse(String text) {
        if (text == null || text.isEmpty()) return text;
        Result r = new Result(text, "", 0, false);
        TreeSet<Result> s = parse(r);
        System.out.println(s);
        return s.first().output;
    }
    
    private TreeSet<Result> parse(Result r) {
        if (r.input.isEmpty()) {
            TreeSet<Result> s = new TreeSet<Result>();
            s.add(r);
            return s;
        }
        
        Set<String> found = mDict.match(r.input);
        if (found.isEmpty()) {
            char c = r.input.charAt(0);
            String in = r.input.substring(1);
            String out = r.output;
            if (r.lastFound) out += " ";
            out += c;
            int unr = r.unrec + 1;
            Result res = new Result(in, out, unr, false);
            return parse(res);
        }
        
        TreeSet<Result> s = new TreeSet<Result>();
        for (String word : found) {
            String in = r.input.substring(word.length());
            String out = r.output;
            if (!out.isEmpty()) out += " ";
            out += word;
            Result res = new Result(in, out, r.unrec, true);
            s.addAll(parse(res));
        }
        return s;
    }

    
    private static interface Dict {
        Set<String> match(String text);
    }
    
    private static class DictImpl implements Dict {
        static class Node {
            Character c;
            boolean isLeaf = false;
            HashMap<Character, Node> children 
                = new HashMap<Character, Node>();
            Node(Character ch) {
                c = ch;
            }
        }
        
        private Node mRoot = new Node(null);
        
        DictImpl(String[] words) {
            if (words == null) return;
            for (String w : words) {
                insertBelow(mRoot, w);
            }
        }
        
        void insertBelow(Node node, String w) {
            if (w.isEmpty()) {
                node.isLeaf = true;
                return;   
            }
            char c = w.charAt(0);
            Node child = node.children.get(c);
            if (child == null) {
                child = new Node(c);
                node.children.put(c, child);
            }
            insertBelow(child, w.substring(1));
        }
        
        @Override
        public Set<String> match(String text) {
            return match(mRoot, text, "", new HashSet<String>());
        }
        
        //  .       0 
        //  w       1
        //  o       2
        //  r       3
        //  l  .d   4  .5
        // .d  .s   6   7
        
        // 0, "words", "", []
        // 1, "ords", "w", []
        // 2, "rds", "wo", []
        // 3, "ds", "wor", []
        // 5, "s", "word", ["word"]
        // 7, "", "words", ["word", "words"]
        private Set<String> match(Node node, String text, String word, Set<String> res) {
            if (text.isEmpty()) return res;
            char c = text.charAt(0);
            Node child = node.children.get(c);
            if (child == null) {
                return res;
            }
            word += c;
            if (child.isLeaf) {
                res.add(word);
            }
            return match(child, text.substring(1), word, res); 
        }
    }        
    
    private static class Result implements Comparable<Result>{
        String input;
        String output;
        int unrec;
        boolean lastFound = true;
        Result(String in, String out, int unr, boolean lastFnd) {
            input = in;
            output = out;
            unrec = unr;
            lastFound = lastFnd;
        }
        
        @Override
        public String toString(){
            return "[\"" + input + "\", \"" + output + "\", " 
                + unrec + ", " + lastFound + "]";
        }
        
        @Override
        public int compareTo(Result r) {
            return unrec - r.unrec;
        }
    }
}