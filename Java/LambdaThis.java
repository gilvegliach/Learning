import java.util.*;
import java.util.function.*;

public class LambdaThis {
  public static void main(String[] args) {
    List<String> a = new ArrayList<>();
    a.add("one");
    a.add("two");
    a.stream()
     .map(s -> {
       String t = "1 ";// + this; // this cannot be accessed
       return t + s;
     })
     .forEach(System.out::println);
     
    System.out.println("---");
    Function<String, String> f = new Function<String, String>() {
      String t = "2 ";
      @Override
      public String apply(String s) {
        return this.t + s; // this is allowed here
      } 
    };
    a.stream().map(f).forEach(System.out::println); 
  }
}
