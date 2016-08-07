import java.util.Stack;

public class StackedQueue<T> implements Queue<T> {
	private final Stack<T> mEnqueuingStack = new Stack<T>();
	private final Stack<T> mDequeuingStack = new Stack<T>();
	private Stack<T> mCurrentStack = mEnqueuingStack;

    public StackedQueue() { }

    /** Enques an element into this queue */	
	@Override 
	public void enqueue(T e) {
		if (mCurrentStack != mEnqueuingStack) {
			switchStack();
		}
		mCurrentStack.push(e);
	}
	
	/** 
     * Deques an element from this queue or returns null if
     * this queue is empty
     */
	@Override
	public T deque() {
		if (mCurrentStack != mDequeuingStack) {
			switchStack();
		}
		return mCurrentStack.isEmpty() ? null 
            : mCurrentStack.pop();
	}


	
	private void switchStack() {
		Stack<T> curr = mCurrentStack;
        Stack<T> other = 
            (mCurrentStack == mEnqueuingStack) ?
				mDequeuingStack :
 	            mEnqueuingStack;
        while (!curr.isEmpty()) {
	        other.push(curr.pop());
        }
        mCurrentStack = other;
    }

    @Override
    public String toString() {
	    Stack<T> curr = mCurrentStack;
	    StringBuilder sb = new StringBuilder("[");
	    if (mCurrentStack == mEnqueuingStack) {
	        for (int i = 0, sz = curr.size();
                    i < sz; i++) {
                sb.append(curr.get(i));
                if (i != sz - 1) sb.append(", ");
            }
		} else {
			for (int sz = curr.size(), i = sz - 1;
					i >= 0; i--) {
				sb.append(curr.get(i));
				if (i != 0) sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}


	public static void main(String[] args) {
		Queue<Integer> q = new StackedQueue<Integer>();
		System.out.println(q);   // []
		q.enqueue(1);
		q.enqueue(2);
		System.out.println(q);   // [1, 2]
		q.deque();
		System.out.println(q);   // [2]
		q.deque();			 
		System.out.println(q);   // []
        System.out.println(q.deque());  // null
    } 
}