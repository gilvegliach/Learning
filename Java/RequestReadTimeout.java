import java.net.*;
import java.io.*;

public class RequestReadTimeout {
  // nc -l 8999
  public static void main(String[] args) throws Exception {
    String url = "http://localhost:8999";
		URL obj = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
    conn.setReadTimeout(3000);
		System.out.println(conn.getResponseCode());
  }
}
