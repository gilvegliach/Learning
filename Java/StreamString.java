public class StreamString {
  public static void main(String[] args) {
    System.out.println(allUppercase("asd"));
    System.out.println(allUppercase("ASD"));
    System.out.println(allUppercase(""));
  }

  static boolean allUppercase(String s) {
    return s.chars().allMatch(k -> Character.isUpperCase(k));
  }
}
