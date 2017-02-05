// Uses \u202e RIGHT-TO-LEFT OVERRIDE which is not printable
public class WeirdChars {
    public static void main(String[] args) {
        for (char c⁯‮h = 0; c⁯‮h < Character.MAX_VALUE; c⁯‮h++)
            if (Character.isJavaIdentifierPart(c⁯‮h) && !Character.isJavaIdentifierStart(c⁯‮h))
                System.out.printf("%04x <%s>%n", (int) c⁯‮h, "" + c⁯‮h);
    }
}