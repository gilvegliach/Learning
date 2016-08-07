import java.util.Iterator;
import java.util.Arrays;

public class CircularArray<T> implements Iterable<T> {
    private final T[] mArray;
    private int mOffset = 0;
 
    public CircularArray(int size) {
        mArray = (T[]) new Object[size];//Arrays.newInstance(T.class, size);
    }
    
    public CircularArray(T[] array) {
        mArray = Arrays.copyOf(array, array.length);
    }

    /** 
     * Rotates left the array. 
     * E.g [1 2 3 4].rotateLeft(2) -> [3 4 1 2] 
     */
    public CircularArray<T> rotateLeft(int positions) {
        int size = mArray.length;
        positions %= size;
        mOffset = (mOffset + positions) % size;
        return this;
    }
 
    public CircularArray<T> rotateRight(int positions) {
        int size = mArray.length;
        positions %= size;
        mOffset = (mOffset - positions + size) % size;
        return this;
    }

    public T elemAt(int position) {
        int size = mArray.length;
        int index = (mOffset + position) % size;
        return mArray[index];
    }

    public void changeAt(int position, T newElem) {
        int size = mArray.length;
        int index = (mOffset + position) % size;
        mArray[index] = newElem;
    }

    public int size() {
        return mArray.length;
    }

    class CircularArrayIterator implements Iterator<T> {
        int mSeen = 0;
        public boolean hasNext() {
            return mSeen < mArray.length;        
        }
 
        public T next() {
            return elemAt(mSeen++);
        }

        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported");
        }
    }

    public Iterator<T> iterator() {
        return new CircularArrayIterator();
    }   

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<T> iter = iterator();
        while (iter.hasNext()) {
            T elem = iter.next();
            sb.append(elem.toString());
            if (iter.hasNext())
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Integer[] arrInt = new Integer[]{ 1, 2, 3, 4 };
        CircularArray<Integer> arr = new CircularArray<Integer>(arrInt);
        System.out.println(arr);
        arr.rotateLeft(2);
        System.out.println(arr);
        arr.rotateRight(5);
        System.out.println(arr); 
    }
}
