import java.util.Stack;

public class Trees {
    public static void main(String[] args) {
        Node<Integer> root = new Node(1, 
            new Node(2, 
                new Node(3, 
                    new Node(4, null, null),
                    new Node(5, null, null)),
                new Node(6, null, null)),
            new Node(7, 
                null, 
                new Node(8,
                    new Node(9, null, null),
                    null)));
        
        System.out.println("post-order");
        postOrder(root);
        System.out.println("\npost-order-iter2");
        postOrderIter2(root);
        
        //Node<Integer> root2 = new Node(2,
        //    new Node(3, 
        //        new Node(4, null, null),
        //        new Node(5, null, null)),
        //    new Node(6, null, null));
        //System.out.println("\nbal2: " + isBalanced(root2));
    }
    
    static class Node<T> {
        T elem;
        Node<T> left, right;
        boolean visited = false;
        
        Node(T elem, Node<T> left, Node<T> right) {
            this.elem = elem;
            this.left = left;
            this.right = right;
        }
    }
    
    
    //    void pord(node) {
    //      if (node == null) return;
    // 0:   post(node.left);
    // 1:   post(node.right);
    // 2:   visit(node);
    //    }
    static <T> void postOrderIter2(Node<T> node) {
        Stack<Node<T>> args = new Stack<Node<T>>();
        Stack<Integer> retaddr = new Stack<Integer>();
        Node<T> curr = node;
        int pc = 0; // program counter

        while (true) {
            if (curr == null) {
                if (args.empty()) return;
                curr = args.pop();
                pc = retaddr.pop();
            } else if (pc == 0) {
                args.push(curr);
                retaddr.push(1);
                curr = curr.left;
                pc = 0;
            } else if (pc == 1) {
                args.push(curr);
                retaddr.push(2);
                curr = curr.right;
                pc = 0;
            } else if (pc == 2) {
                visit(curr);
                if (args.empty()) return;
                curr = args.pop();
                pc = retaddr.pop();
            }
        }
    }        
    
    static <T> boolean isBalanced(Node<T> node) {
        return height2(node) != -2;
    }
    
    static <T> int height2(Node<T> node) {
        if (node == null) return -1;
        
        int hl = height2(node.left); 
        if (hl == -2) return -2;
        
        int hr = height2(node.right);
        if (hr == -2) return -2;
        if (Math.abs(hr - hl) > 1) return -2;
        return 1 + Math.max(hl, hr);
    }        
    
    
    static <T> void visit(Node<T> node) {
        System.out.println("<" + node.elem.toString() + ">");
    }
    
    static <T> int height(Node<T> node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), 
            height(node.right));
    }
    
    static <T> void preOrderIter(Node<T> node) {
        Stack<Node<T>> stack = new Stack<Node<T>>();
        stack.push(node);
        while (!stack.empty()) {
            Node<T> v = stack.pop();
            if (v == null) continue;
            visit(v);
            stack.push(v.right);
            stack.push(v.left);    
        }
    }
    
    // Equivalent to wiki algo: the stack is empty after the inner 
    // while iff current was null before the inner while (no pushes)
    // and the stack was also empty then. So we break at the beginning
    // of the outer while iff stack.empty() && node == null. QED
    static <T> void inOrderIter(Node<T> node) {
        Stack<Node<T>> stack = new Stack<Node<T>>();
        Node<T> current = node;
        while (true) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            // Post: current == null
            if (stack.empty()) return;
            current = stack.pop();
            visit(current);
            current = current.right;
        }
    }
    
    static <T> void preOrder(Node<T> node) {
        if (node == null) return;
        visit(node);
        preOrder(node.left);
        preOrder(node.right);
    }
        
    static <T> void inOrder(Node<T> node) {
        if (node == null) return;
        inOrder(node.left);
        visit(node);
        inOrder(node.right);
    }
    
    static <T> void postOrder(Node<T> node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        visit(node);
    }               
}