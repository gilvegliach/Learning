import java.util.*;

public class KnightTour {
    public static void main(String[] args) {
        Pos src = Pos.of(0, 0);
        Pos dst = Pos.of(0, 1);
        List<Pos> seq = moves(src, dst, 8);
        print(8, seq);
    }

    static void print(int size, List<Pos> seq) {
        if (seq == null) {
            System.out.println("No sequence");
            return;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String str = " .. ";
                Pos pos = Pos.of(i, j);
                if (seq.contains(pos)) {
                    int index = seq.indexOf(pos);
                    str = index > 9 ? " " + index + " "
                        : "  " + index + " ";
                }
                System.out.print(str);
            }
            System.out.println();
        }
    }

    static class Pos {
        int i;
        int j;
        Pos(int ii, int jj) {
            i = ii;
            j = jj;
        }
    
        static Pos of(int i, int j) {
            return new Pos(i, j);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pos)) return false;
            Pos pos = (Pos) o;
            return i == pos.i && j == pos.j;
        }

        @Override
        public int hashCode() {
            return i ^ j;
        }
    }
    static List<Pos> moves(Pos src, Pos dst, int size) {
        return moves(src, dst, size, new ArrayList<Pos>()); 
    }

    static List<Pos> moves(Pos src, Pos dst, int size, List<Pos> solution) {
        solution.add(src);
        if (src.equals(dst)) {
            return solution;
        }
        List<Pos> adj = findAdjacent(src, size, solution);
        for (Pos pos : adj) {
            List<Pos> result = moves(pos, dst, size, solution);
            if (result != null) return result;
        }
        solution.remove(solution.size() - 1);
        return null;
    }

    static List<Pos> findAdjacent(Pos pos, int size, List<Pos> occupied) {
        int i = pos.i;
        int j = pos.j;
        List<Pos> all = new ArrayList<Pos>() {{
            add(Pos.of(i - 2, j - 1));
            add(Pos.of(i - 2, j + 1));
            add(Pos.of(i - 1, j + 2));
            add(Pos.of(i + 1, j + 2));
            add(Pos.of(i + 2, j + 1));
            add(Pos.of(i + 2, j - 1));
            add(Pos.of(i + 1, j - 2));
            add(Pos.of(i - 1, j - 2));
        }};
        List<Pos> adj = new ArrayList<Pos>();
        for (Pos p : all) {
            if (p.i < 0 || p.j < 0 || p.i >= size || p.j >= size
                    || occupied.contains(p)) {
                continue;        
            }
            adj.add(p);
        }
        return adj;
    }
}   

