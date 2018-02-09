import java.util.function.*;

public class NoDiamondOpForInnerClasses {
  public static void main(String[] args) {
    // Compiles in Java 9 but not in Java 8
    Function<String, String> f = new Function<>() {
      public String apply(String s) {
        return "a";
      }
    };
  }
}
