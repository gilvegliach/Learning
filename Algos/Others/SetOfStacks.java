import java.util.ArrayList;
import java.util.Stack;
import java.util.EmptyStackException;

public class SetOfStacks<T> {
	private final mThreshold;
	private final ArrayList<Stack<T>> mStacks;
private int mCurrent;	

public SetOfStacks(int n, int threshold) { 
	if (n < 1) {
throw new IllegalArgumentException(
“n must be greater than or equal to 1”);
		}
		if (threshold < 1) {
			throw new IllegalArgumentException(
				“threshold must be greater or equal to 1”);
		}
mStack = new ArrayList<Stack<T>>(n);
	mThreshold = threshold;
	mCurrent = 0;
}

public void push(T e) {
	Stack<T> s = mStacks.get(mCurrent);
	s.push();
	if (s.size() == mThreshold) {
		if (mCurrent == mStacks.size() - 1) {
			mStacks.add(new Stack<T>());
		}
		mCurrent++;
	}
}

public T pop() {
	Stack<T> s = mStacks.get(mCurrent);
	while (s.isEmpty()) {
		if (mCurrent == 0) {
throw new EmptyStackException();
}
mCurrent--;
s = mStacks.get(mCurrent);
		}
		return s.pop();
	}
	
	public T popAt(int i) {
		if (i < 0 || i >= mStacks.size()) {
			throw new IndexOutOfBoundsException(
"i must be in [0, #stacks)");
		}
		Stack<T> s = mStacks.get(i);
		return s.pop(); // may throw EmptyStackException 
	}
}