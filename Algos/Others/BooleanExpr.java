import java.util.HashMap;

public class BooleanExpr {
    static HashMap<Key, Integer> sCache;   

    public static void main(String[] args) {
        int ways = waysOfParenthesizing("1^0|0|1", false);
        System.out.println(ways);
        assert ways == 2;
    }

    static int waysOfParenthesizing(String expr, boolean result) {
        try {
            sCache = new HashMap<>();        
            return waysHelper(expr, 0, expr.length() - 1, result);
        } finally {
            sCache = null;
        }
    }

    static int waysHelper(String expr, int l, int r, boolean result) {
        if (l == r) {
            char c = expr.charAt(l);
            int value = Character.digit(c, 10);
            return ((value == 1 && result) || (value == 0 && !result)) ? 1 : 0;
        }

        Key key = Key.of(l, r, result);
        Integer value = sCache.get(key);
        if (value != null) {
            return value.intValue();
        }
        
        int totalWays = 0;
        for (int i = l + 1; i < r; i++) {
            char c = expr.charAt(i);
            if (!isOperator(c)) continue;

            int waysLeftTrue = waysHelper(expr, l, i - 1, true);
            int waysRightTrue = waysHelper(expr, i + 1, r, true);
            int waysLeftFalse = waysHelper(expr, l, i - 1, false);
            int waysRightFalse = waysHelper(expr, i + 1, r, false);
            
            int ways = -1;
            switch (c) {
                case '&': 
                    if (result) {
                       ways = waysLeftTrue * waysRightTrue;
                    } else {
                       ways = waysLeftTrue * waysRightFalse 
                           + waysLeftFalse * waysRightTrue 
                           + waysLeftFalse * waysLeftFalse;
                    }
                    break;
                case '|':
                    if (result) {
                        ways = waysLeftTrue * waysRightFalse
                            + waysLeftFalse * waysRightTrue
                            + waysLeftTrue * waysRightTrue;
                    } else {
                        ways = waysLeftFalse * waysRightFalse;
                    }
                    break;
                case '^':
                    if (result) {
                        ways =  waysLeftTrue * waysRightFalse
                            + waysLeftFalse * waysRightTrue;
                    } else {
                        ways = waysLeftTrue * waysRightTrue
                            + waysLeftFalse * waysRightFalse;
                    }
                    break;
            }
            totalWays += ways;
        }

        sCache.put(key, totalWays);
        return totalWays;
    }

    static boolean isOperator(char c) {
        return c == '&' || c == '|' || c == '^';
    }

    static class Key {
        int l;
        int r;
        boolean result;
        private Key(int l, int r, boolean result) {
            this.l = l;
            this.r = r;
            this.result = result;
        }
        
        static Key of(int l, int r, boolean res) {
            return new Key(l, r, res);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key k = (Key) o;
            return l == k.l && r == k.r && result == k.result;
        }
        @Override
        public int hashCode() {
            int res = result ? 1 : 0;
            return l ^ r ^ res;
        }
    }
}
