public class LinkedSum {
    static class ListNode {
        public int val;
        public ListNode next;
        ListNode(int x) { val = x; next = null; }
    }
    
    
    public static void main(String[] args) {
        int[] a = { 95 , 59 , 26 , 16 , 31 , 39 , 29 , 8 , 74 , 14 , 41 , 41 , 
    	    64 , 88 , 34 , 21 , 67 , 23 , 17 , 41 , 3 , 38 , 4 , 45 , 93 , 92 , 99 , 24};
            
        ListNode h = new ListNode(a[0]), curr = h;
        for (int i = 1; i < a.length; i++) {
            curr.next = new ListNode(a[i]);
            curr = curr.next;
        }
        subtract(h);
        while (h != null) {
            System.out.print(h.val + " -> ");
            h = h.next;
        }
    }
    
    public static ListNode subtract(ListNode a) {
    	    // 95 -> 59 -> 26 -> 16 -> 31 -> 39 -> 29 -> 8 -> 74 -> 14 -> 41 -> 41 -> 
    	    // 64 -> 88 -> 34 -> 21 -> 67 -> 23 -> 17 -> 41 -> 3 -> 38 -> 4 -> 45 -> 93 -> 92 -> 99 -> 24
    	    //           
    	    if (a == null) return null;
    	    ListNode head = a, slow = a, fast = a;
    	    while (fast != null) {
    	        slow = slow.next;
    	        fast = fast.next;
    	        if (fast == null) break;
    	        fast = fast.next;
    	    }
    	    ListNode curr = slow, next = curr.next;
            
    	    while (next != null) {
    	        ListNode t = next;
    	        next = next.next;
    	        t.next = curr;
    	        curr = t;
    	    }
    	    next = curr.next;
    	    while (next != null) {
                ListNode t = next.next;
                System.out.println("curr: " + curr.val + ", next: " + next.val + "t: " + t.val);
    	        a.val = curr.val - a.val;
    	        a = a.next;
    	        //ListNode t = next.next;
    	        next.next = curr;
    	        curr = next;
    	        next = t;
                
    	    }
    	    if (a != curr) {
    	        a.val = curr.val - a.val;
    	    }
    	    return head;
    	}

}
