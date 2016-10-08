// You are given a set of coins S. In how many ways can you make sum N assuming you have 
// infinite amount of each coin in the set.
//
// Note : Coins in set S will be unique. Expected space complexity of this problem is O(N).
//
public class CoinSumInfinite {
    public int coinchange2(ArrayList<Integer> a, int b) {
        int n = a.size();
        int[] count = new int[b + 1];
        count[0] = 1;
        for (int i = 0; i < n; i++) {
            int coin = a.get(i);
            for (int j = coin; j <= b; j++) {
                count[j] = (count[j] + count[j - coin]) % 1000007;
            }
        }
        return count[b];
    }
    
    // Timeouts
    static class P { int i; int b; P(int ii, int bb) { i = ii; b = bb; }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof P)) return false;
            P p = (P) o;
            return i == p.i && b == p.b;
        }
        @Override
        public int hashCode() { return i ^ b; }
    }
    
    int coin(ArrayList<Integer> a, int b, int i, HashMap<P, Integer> m) {
        int n = a.size();
        if (b < 0) return 0;
        if (b == 0) return 1;
        if (i < 0) return 0;
        P key = new P(i, b);
        if (m.containsKey(key)) return m.get(key);
        
        int elem = a.get(i);
        int res = coin(a, b - elem, i, m) + coin(a, b, i - 1, m);
        res %= 1000007;
        
        m.put(key, res);
        return res;
    }
}