import java.util.ArrayList;
import java.util.List;

public class Binary {
    private static final String PREFIX = "0.";
    private static final double EPS = Math.pow(2, -32);
    public static int compare(double d1, double d2) {
        double diff = d1 - d2;
        if (diff < -EPS) return -1;
        if (diff > EPS) return 1;
        return 0;
    }
    
    public static String arr2str(double[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (Double d : arr) sb.append(d + ",");
        sb.replace(sb.length() - 1, sb.length(), "]");
        return sb.toString();
    }
    
    // 0. <= x <= 1.
    public static String dec2bin(double x) {
        if (x < 0.) { 
            throw new IllegalArgumentException("x must be in [0., 1.]");
        }
        if (x > 1.) { 
            throw new IllegalArgumentException("x must be in [0., 1.]");
        }

        if (compare(x, 0.) == 0) return "0.0";
        if (compare(x, 1.) == 0) return "1.0";
        // Post-condition: x in (0,1)

        int count = 0;
        StringBuilder sb = new StringBuilder(PREFIX);
        double[] period = new double[32];
        while (x > 0) {
            //System.out.println("" + x + " " + count + " " + arr2str(period));
            for (int i = 0; i < count; i++) {
                if (compare(x, period[i]) == 0) {
                    sb.insert(PREFIX.length() + i, "(");
                    sb.append(")");
                    return sb.toString();
                }
            }
            if (count == 32) return "ERROR";
            period[count] = x;	
    
            x *= 2.;
            String digit = (x >= 1.) ? "1": "0";
            if (x >= 1) x -= 1.;
            sb.append(digit);
            count++;
        } 
        return sb.toString();
    }

    public static void main(String[] args) {
       // System.out.println(dec2bin(0.75));
       // System.out.println(dec2bin(0.));
        System.out.println(dec2bin(1.));
        System.out.println(dec2bin(0.1));
        System.out.println(dec2bin(0.2));
        System.out.println(dec2bin(0.3));
       // System.out.println(dec2bin(.123456));
    }


// Runs:
// 0.1, 0.0(0011)
//  0, 0.1, "0.",     []
//  1, 0.2, "0.0",    [0.1]
//  2, 0.4, "0.00",   [0.1, 0.2]
//  3, 0.8, "0.000",  [0.1, 0.2, 0.4]
//  4, 0.6, "0.0001", [0.1, 0.2, 0.4, 0.8]
//  5, 0.2, "0.00011" 
//  6, 0.4, "0.000110"

// 0.75
// (i,x,sb)
// init: 0, 0.75
// 1, 0.5, "0.1"
// 2, 0.0, "0.11", stop
}