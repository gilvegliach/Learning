import java.util.*;

public class PowerSet {
    static <T> Set<Set<T>> powerSet(Set<T> s) {
        if (s.isEmpty()) { 
            return new HashSet<Set<T>>() {{
                add(new HashSet<T>());
            }};
        }
        T elem = choose(s);
        s.remove(elem);
        Set<Set<T>> subsets = powerSet(s);
        Set<Set<T>> result = new HashSet<Set<T>>();
        for (Set<T> subset : subsets) {
            result.add(subset);
            Set<T> subsetWithElem = new HashSet<T>(subset);
            subsetWithElem.add(elem);
            result.add(subsetWithElem);
        }
        return result;
    }

    static <T> T choose(Set<T> s) {
        Iterator<T> i = s.iterator();
        return i.next();
    }

    static <T> void print(Set<T> s) {
        System.out.print("{");
        for (Iterator<T> i = s.iterator(); i.hasNext(); ) {
            T elem = i.next();
            System.out.print(elem);
            if (i.hasNext()) System.out.print(", ");
        }
        System.out.println("}");
    }

    public static void main(String[] args) {
        Set<Integer> s = new HashSet<Integer>() {{
            add(1); add(2); add(3);
        }};
        Set<Set<Integer>> subsets = powerSet(s);
        for (Set<Integer> subset : subsets) {
            print(subset);
        }
    }
}
