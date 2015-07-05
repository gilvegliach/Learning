// Casting a null reference does not throw an exception
public class CastOnNullTest
{

	public static void main(String[] args)
	{
		B b = null;
		A a = (A) b; // No exception
		System.out.println("done");
	}
}

class A
{
}

class B extends A
{
}