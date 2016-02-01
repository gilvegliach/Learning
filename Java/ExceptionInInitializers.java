public class ExceptionInInitializers {
    static {
        if (false) {
        } else {
           // throw new Exception(); // compilation error
        }
    }

    {
        if (false) {
        } else {
            throw new Exception();
        }
    }

    // Must throw because non-static initialiazer throws exception 
    public ExceptionInInitializers() throws Exception
    { 
    }

    public static void main(String[] args) throws Exception {
        new ExceptionInInitializers();
    }
}
