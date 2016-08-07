public class NegativeOverflow {
    public static void main(String[] args) {
        int i = Integer.MIN_VALUE;
        System.out.println(i);
        i--;
        System.out.println(i);
    }
}
