public class ThrowThrowable {
	// Compiles with Java 7 but not with Java  6
	public static void main(String[] args) {
		try {
			throw new RuntimeException();
		} catch (Throwable t) {
			throw t;
		}
	}
}