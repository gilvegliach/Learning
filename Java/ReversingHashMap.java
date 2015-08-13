import java.util.HashMap;
import java.util.Map;

public class ReversingHashMap {
	public static <K, V> void print(HashMap<K, V> h) {
		for (Map.Entry<K, V> e : h.entrySet()) {
			System.out.print(e.getKey() + "->" 
				+ e.getValue() + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		HashMap<Integer, String> h = new HashMap<>();
		h.put(1, "a");
		h.put(2, "b");
		h.put(3, "c");
		print(h);
		
		HashMap<String, Integer> rh = new HashMap<>();
		for (Map.Entry<Integer, String> e : h.entrySet()) {
			rh.put(e.getValue(), e.getKey());
		}
		print(rh);
	}
}