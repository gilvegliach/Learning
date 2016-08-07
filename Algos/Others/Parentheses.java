// ctci 9.6
public class Parentheses {
    static void parentheses(int n) {
        char[] buff = new char[2 * n];
        par(2 * n, n, 0, buff);
    }

    static void par(int totalLeft, int openAvail, int currOpen, 
            char[] buff) {
        if (totalLeft == 0) {
            System.out.println(buff);
            return;
        }
        int index = buff.length - totalLeft;
        if (openAvail > 0) {
            buff[index]= '(';
            par(totalLeft - 1, openAvail - 1, currOpen + 1, buff);
        }
        if (currOpen > 0) {
            buff[index] = ')';
            par(totalLeft - 1, openAvail, currOpen - 1, buff);
        } 
    }

    public static void main(String[] args) {
        parentheses(3);
    }
}
