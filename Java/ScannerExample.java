import java.util.*;

public class ScannerExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);//.useDelimiter("[\n ]");
        System.out.println("delimiter: " + sc.delimiter());
        while (sc.hasNext()) {
            System.out.println(sc.next());
        }
    }
}
