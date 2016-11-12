import java.io.*;
import java.util.*;

public class Candy {

    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int n = sc.nextInt();
      int[] a = new int[n], b = new int[n];
      for (int i = 0; i < n; i++) a[i] = sc.nextInt();
      Arrays.fill(b, 1);
      
      for (int i = 1; i < n; i++) {
          if (a[i] > a[i - 1]) b[i] = b[i - 1] + 1;
      }
      for (int i = n - 2; i >= 0; i--) {
          if (a[i] > a[i + 1]){
            int val = b[i + 1] + 1;
            if (val > b[i]) b[i] = val;  
          } 
      }
      long sum = 0L;
      for (int k : b) sum += k;
      System.out.println(sum);
    }
}
