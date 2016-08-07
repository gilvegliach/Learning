import java.util.LinkedList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;

public class LinkedListUtils {

public static void removeDuplicates(
LinkedList<Integer> list) {
	if (list == null) return;
HashSet<Integer> set = new HashSet<Integer>();
Iterator<Integer> iter = list.iterator();
while (iter.hasNext()) {
	Integer elem = iter.next();
	if (set.contains(elem)) {
		iter.remove();
	} else {
		set.add(elem);
	}
}
}


public static void removeDuplicates2(
        LinkedList<Integer> list) {
    if (list == null) return;
    ListIterator<Integer> i = list.listIterator();
    while (i.hasNext()) {
	    Integer elem = i.next();
	    int from = i.nextIndex();
	    ListIterator<Integer> j = list.listIterator(from);
	    boolean duplicated = false;
	    while (j.hasNext()) {
		    Integer elem2 = j.next();
		    if (elem.equals(elem2)) { 
		        duplicated = true;
		        break;
		    }
    	}
    	if (duplicated) i.remove();
    }
    }
    
    
/** 
 * Gets the kth to last element in the {@code LinkedList}
 * list (0 based). Returns null if the list is null or k is 
 * outside the boundaries
 */
public static <T> T getFromTail(LinkedList<T> list, int k) {
	// It does not use list.size() as a requirement
	if (list == null || list.isEmpty() || k < 0) {
        return null;
    }

    Iterator<T> i = list.iterator();
    while (k >= 0) {
        if (!i.hasNext()) return null;
		i.next();
		k--;
	}
	// i.next() now points at the (k+1)-th elem
	Iterator<T> j = list.iterator();
	while (i.hasNext()) {
		i.next();
		j.next();
	}
	return j.next();
}





public static void main(String[] args) {
	LinkedList<Integer> list = new LinkedList<Integer>();
	removeDuplicates2(list);
    System.out.println(list); // []
    list.add(1);
	list.add(2);
	list.add(3);
	removeDuplicates2(list);
	System.out.println(list);  // [1, 2, 3]
    list.add(3);
	list.add(4);
	list.add(2);
	list.add(2);
	removeDuplicates2(list);
	System.out.println(list);  // [1, 2, 3, 4]
	for (int i = 0; i < list.size(); i++) {
	    System.out.println("i=" + i + ", val=" + getFromTail(list, i));
	}
}

}