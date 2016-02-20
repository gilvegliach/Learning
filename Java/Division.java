// Shows results for / and % on negative numbers
public class Division {
    public static void main(String[] args) {
        System.console().format("%d / %d = %d, %d %% %d = %d\n", 9,2,9/2,9,2,9%2);
        System.console().format("%d / %d = %d, %d %% %d = %d\n", -9,-2,-9/-2,-9,-2,-9%-2);
        System.console().format("%d / %d = %d, %d %% %d = %d\n", -9,2,-9/2,-9,2,-9%2);
        System.console().format("%d / %d = %d, %d %% %d = %d\n", 9,-2,9/-2,9,-2,9%-2);
       
        System.out.println();

        System.console().format("%d / %d = %d, %d %% %d = %d\n", 9,2,q(9,2),9,2,r(9,2));
        System.console().format("%d / %d = %d, %d %% %d = %d\n", -9,-2,q(-9,-2), -9,-2,
                r(-9,-2));
        System.console().format("%d / %d = %d, %d %% %d = %d\n", -9,2,q(-9,2),-9,2,
                r(-9,2));
        System.console().format("%d / %d = %d, %d %% %d = %d\n", 9,-2,q(9,-2),9,-2,
                r(9,-2));
    }

    // Euclidian division, b > r >= 0
    static int q(int a, int b) {
        int adjustment = a < 0 ? 1 : 0;
        return sign(a) * sign(b) * (abs(a) / abs(b) + adjustment);
    }
    
    static int r(int a, int b) {
        return a - q(a, b) * b;
    }

    static int abs(int n) {
        return n >= 0 ? n : -n;
    }

    static int sign(int n) {
        if (n > 0) return 1;
        if (n < 0) return -1;
        return 0;
    }
}
