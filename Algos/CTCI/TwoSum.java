// ctci 18.4
public class TwoSum {
    static int countTwo(int n) {
        int sum = 0;
        int w = 1;
        while (n / w != 0) {
            int d = (n / w) % 10;
            sum += (n / (w * 10)) * w;
            if (d == 2) {
                sum += (n % w) + 1;
            } else if (d >= 3) {
                sum += w;
            }
            w *= 10;
        }
        return sum;
    }
    
    public static void main(String[] args) {
        int[] a = { 25, 122, 123 };
        int[] r = {  9, 26, 27 };
        for (int i = 0; i < a.length; i++) {
            int res = countTwo(a[i]);
            System.out.println(a[i] + " : " + res);
            assert res == r[i];
        }
    }
}

