public class StrangeSum {
    static int sum(int a, int b) {
        int bc = 0;
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            int ba = get(a, i);
            int bb = get(b, i);
            int bs = sum(ba, bb, bc);
            bc = carry(ba, bb, bc);
            sum |=  bs << i;
        }
        return sum;
    }

    static int get(int a, int i) {
        return (a >> i) & 1;
    }

    static int sum(int ba, int bb, int bc) {
        return ba ^ bb ^ bc;
    }

    static int carry(int ba, int bb, int bc) {
        return ba == 1 ? bb | bc : bb & bc;
    }

    public static void main(String[] args) {
        assert sum(1, 0) == 1;
        assert sum(5, 5) == 10;
        assert sum(123, 456) == 579;
        assert sum(9999, 1) == 10000;
        assert sum(9929, 101) == 10030;
        assert sum(98, -9) == 89;
        assert sum(98, -1239) == -1141;
    }
}          
      
