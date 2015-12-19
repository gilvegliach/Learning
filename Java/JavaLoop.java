public class JavaLoop {
    public static void main(String[] args) {
        for (float f = 0.f; ; f++) {
            float u = Math.ulp(f);
            if (u > 1.f) {
                System.out.println("f = " + f + ", u = " + u);
                break;
            }
        }
        System.out.println("ulp = " + Math.ulp(2000000000));
    }
}
