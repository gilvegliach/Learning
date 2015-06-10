public class InstanceOfByteCode {
    static class A {
    }

    static class B extends A {
    }

    public static void main(String[] args) {
        A a = new B();
        if (a instanceof B) {
            System.out.println("B");
        }
    }
}


//Compiled from "InstanceOfByteCode.java"
//public class InstanceOfByteCode {
//  public InstanceOfByteCode();
//    Code:
//       0: aload_0       
//       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
//       4: return        
//
//  public static void main(java.lang.String[]);
//    Code:
//       0: new           #2                  // class InstanceOfByteCode$B
//       3: dup           
//       4: invokespecial #3                  // Method InstanceOfByteCode$B."<init>":()V
//       7: astore_1      
//       8: aload_1       
//       9: instanceof    #2                  // class InstanceOfByteCode$B
//      12: ifeq          23
//      15: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
//      18: ldc           #5                  // String B
//      20: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
//      23: return        
//}
