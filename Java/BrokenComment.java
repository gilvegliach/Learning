public class BrokenComment {
  public static void main(String[] args) {
    // \uuups, this is broken!
    System.out.println("Doesn't compile");
  }
}
