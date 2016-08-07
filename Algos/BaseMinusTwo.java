import java.util.*;

public class BaseMinusTwo {
//
//    1 -2  4 -8 16
// a: 1  1  
//-a: 1  0
//--------------------
//    
//
    public static void main(String[] args) {
        int[] nine = { 1, 0, 0, 1, 1 };
        int[] minusTwentyThree = { 1, 0, 0, 1, 1, 1 };
        int[] zero = { 0 };
        System.out.println(Arrays.toString(negate(zero)));
        System.out.println(Arrays.toString(nine));        
        System.out.println(Arrays.toString(negate(nine)));        
        System.out.println(Arrays.toString(minusTwentyThree));        
        System.out.println(Arrays.toString(negate(minusTwentyThree)));        
    }

    static int negate(int a, int c) {
        return a ^ c;
    }

    static int carry(int a, int c) {
       return a & ~c & 1;
    }
    
    static int[] negate(int[] number) {
        ArrayList<Integer> buff = new ArrayList<>();
        int carry = 0;
        for (int i = 0; i < number.length; i++) {
            buff.add(negate(number[i], carry));
            carry = carry(number[i], carry);
        }
        if (carry == 1) buff.add(1);
        return toIntArr(buff);
    }

    static int[] toIntArr(List<Integer> list) {
        int n = list.size();
        int skip = 0;
        for (int i = n - 1; i > 0; i--) {
            if (list.get(i) == 1) break;
            skip++;
        }
        n -= skip;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
