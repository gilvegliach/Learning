public class RoundTowardZero {
  public static void main(String[] args) {
    System.out.println((int) Math.floor(-5 / 2.0)); // -3
    System.out.println(-5 / 2); // -2, rounded toward zero
  }

}
