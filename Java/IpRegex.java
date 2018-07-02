import java.util.regex.*;
public class IpRegex {
  public static void main(String[] args) {
    System.out.println(mask("1.2.3.4"));
    System.out.println(mask(null));
  }

  static final Pattern IP = Pattern.compile("(\\d{1,3}\\.){3}(\\d{1,3})");
  static String mask(String ip) {
    Matcher m = IP.matcher("" + ip);
    return m.find() ? m.group(2) : null;
  }
}
