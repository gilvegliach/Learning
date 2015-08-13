// Shift op second arg is in [0,31] for ints and in [0,63] for longs
// This code loops forever
public class TrickyShift {
    public static void main(String[] args) {
        int i = 0; 
        while (-1 << i != 0) i++; 
        System.out.println(i);
    }
}
