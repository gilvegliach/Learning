import java.util.Random;

public class Probability {
    public static void main(String[] args) {
        Random r = new Random();
        int success = 0;
        int max = 100000000;
        for (int i = 0; i < max; i++) {
            int event;
            do {
                event = r.nextInt(25);
            } while (event >= 21);
            if (event % 7 == 0) success++;
        }
        System.out.println("prob: " + (success/((double)max)));
    }
}
