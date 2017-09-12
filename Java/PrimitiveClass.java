import java.io.*;
import java.util.*;

public class PrimitiveClass {
  public static void main(String[] args) {
    //primitive
    System.out.println(int.class.getName());
    System.out.println(int.class.getCanonicalName());
    System.out.println(int.class.getSimpleName());

    System.out.println();

    //class
    System.out.println(String.class.getName());
    System.out.println(String.class.getCanonicalName());
    System.out.println(String.class.getSimpleName());

    System.out.println();

    //inner class
    System.out.println(HashMap.SimpleEntry.class.getName());
    System.out.println(HashMap.SimpleEntry.class.getCanonicalName());
    System.out.println(HashMap.SimpleEntry.class.getSimpleName());        

    System.out.println();

    //anonymous inner class
    System.out.println(new Serializable(){}.getClass().getName());
    System.out.println(new Serializable(){}.getClass().getCanonicalName());
    System.out.println(new Serializable(){}.getClass().getSimpleName());
  }
}
