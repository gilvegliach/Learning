import java.util.concurrent.Semaphore;

public class Interleaving extends Thread {
  Semaphore s; int start;
  Interleaving(Semaphore ss, int start) {
    s = ss; this.start = start;
  } 

  void print(int start) throws Exception {
    for (int i = start; i <= 100; i += 2) {
      s.acquire();
      System.out.println(i);
      s.release();
    }
  }

  public void run() {
    try {
      print(start);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) throws Exception {
    Semaphore s = new Semaphore(1, true);
    Interleaving odd = new Interleaving(s, 1);
    Interleaving even = new Interleaving(s, 2);
    s.acquire();
    odd.start();
    even.start();
    s.release();
  }
}
