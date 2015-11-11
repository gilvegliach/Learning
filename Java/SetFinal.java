import java.lang.reflect.*;

public class SetFinal {
    final int i;

    SetFinal() {
        i = 42;
    }

    public static void main(String[] args) throws Exception {
        SetFinal x = new SetFinal();
        System.out.println(x.i);
        Field f = SetFinal.class.getDeclaredField("i");
        f.setAccessible(true);
        f.setInt(x, 7);
        System.out.println(x.i);
    }
}
