import java.util.*;

public class Robot {
	/**
	 * Counts paths between (0,0) and (x,y) for a 
	 * robot that can move only down and right.
	 *
     * @throws IllegalArgumentException 
     * 		if x or y is negative
     */
	public static long countPath(int x, int y) {
		if (x < 0 || y < 0) {
            throw new IllegalArgumentException(
            "x and y must be positive");
        }
        long mem[][] = new long[x+1][y+1];
        return countPathsHelper(0, 0, x, y, mem);
	}

    /**
	 * Counts paths between (0,0) and (x,y) for a 
	 * robot that can move only down and right.
	 *
     * @throws IllegalArgumentException 
     * 		if x or y is negative
     */
	public static long countPath2(int x, int y) {
		if (x < 0 || y < 0) {
            throw new IllegalArgumentException(
            "x and y must be positive");
        }
		long[][] mem = new long[x+1][y+1];
		for (int i=0; i<x+1; i++) {
			mem[i][y] = 1L;
		}
		for (int j=0; j<y+1; j++) {
			mem[x][j] = 1L;
		}
		for (int i=x-1; i>=0; i--) {
			for (int j=y-1; j>=0; j--) {
				mem[i][j] = mem[i+1][j] + mem[i][j+1];
			}
		}
		return mem[0][0];
	}



	private static long countPathsHelper(int startx, 
            int starty, int finalx, int finaly, long mem[][]) {
		if (startx == finalx && starty == finaly) {
			mem[startx][starty] = 1L;
	        return 1L;
		}
		if (mem[startx][starty] != 0) {
			return mem[startx][starty];
		}
		long count = 0L;
		if (startx < finalx) {
            count += countPathsHelper(startx + 1, starty,
                finalx, finaly, mem);
        }
        if (starty < finaly) {
	        count += countPathsHelper(startx, starty + 1,
                finalx, finaly, mem);
        }
        mem[startx][starty] = count;
        return count;
	}

	static class Pair {
		int x, y;
		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Pair)) return false;
			Pair p = (Pair) obj;
			return p.x == x && p.y == y;
		}
		@Override
		public int hashCode() {
			return x ^ y;
		}
	}

	public static List<Pair> pathFinder(int x, int y, 
            List<Pair> blocked) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException(
            "x and y must be positive");
        }
        return pathFinderHelper(0, 0, x, y, blocked);
	}
	
	private static LinkedList<Pair> pathFinderHelper(int startx,
			int starty, int finalx, int finaly, 
			List<Pair> blocked) {
		if (startx == finalx && starty == finaly) {
			LinkedList<Pair> res = new LinkedList<Pair>();
			res.add(new Pair(startx, starty));
	        return res;
		}

		if (startx < finalx) {
			Pair p = new Pair(startx + 1, starty);
			if (blocked.indexOf(p) == -1) {
				LinkedList<Pair> res = pathFinderHelper(
					startx + 1, starty, finalx, finaly,
					blocked);
				if (!res.isEmpty()) {
					res.addFirst(new Pair(startx, starty));
    				return res;
    			}
			}
		}

		if (starty < finaly) {
			Pair p = new Pair(startx, starty + 1);
			if (blocked.indexOf(p) == -1) {
				LinkedList<Pair> res = pathFinderHelper(
					startx, starty + 1, finalx, finaly,
					blocked);
				if (!res.isEmpty()) {
				    res.addFirst(new Pair(startx, starty));
				    return res;
				}
			}
		}
		return new LinkedList<Pair>();
	}

	//  . o . . 
	//  . . . . 
	//  . . o .
	public static void main(String[] args) {
		List<Pair> blocked = new ArrayList<Pair>();
		blocked.add(new Pair(1, 0));
		blocked.add(new Pair(2, 2));
		System.out.println(pathFinder(3, 2, blocked));
	}

}