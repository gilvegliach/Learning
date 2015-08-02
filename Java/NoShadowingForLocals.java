public class NoShadowingForLocals {
    public static void main(String[] args) {
       int i = 0;
       {
          // int i = 2; // compilation error
       }
    }
}
