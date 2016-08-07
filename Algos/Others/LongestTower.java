import java.util.*;

public class LongestTower {
    static class Person {
        int h;
        int w;
        boolean visited;
        Person(int hh, int ww) {
            h = hh;
            w = ww;
        }
    }

    static int longestTower(List<Person> people) {
        return longestTower(people, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    static int longestTower(List<Person> people, int topHeight, int topWeight) {
        int max = 0;
        for (Person p : people) {
            if (p.visited || p.h >= topHeight 
                    || p.w >= topWeight) {
                continue;
            }
            p.visited = true;
            int res = 1 + longestTower(people, p.h, p.w);
            p.visited = false;
            if (res > max) max = res;
        }
        return max;
    }
        
    public static void main(String[] args) {
        List<Person> ppl = new ArrayList<Person>() {{
            add(new Person(65, 100));
            add(new Person(70, 150));
            add(new Person(56, 90));
            add(new Person(75, 190));
            add(new Person(60, 95));
            add(new Person(68, 110));
        }};
        int result = longestTower(ppl);
        System.out.println(result);
        assert result == 6;
    } 
}
