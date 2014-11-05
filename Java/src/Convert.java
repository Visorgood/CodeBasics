public class Convert {
  public static int strToInt(String s) throws IllegalArgumentException  {
    if (s == null || s.length() == 0) {
      throw new IllegalArgumentException ("Invalid string!");
    }
    boolean minus = false;
    if (s.charAt(0) == '-') {
      minus = true;
    }
    int val = 0;
    for (int i = (minus ? 1 : 0); i < s.length(); ++i) {
      char c = s.charAt(i);
      if (!Character.isDigit(c)) {
        throw new IllegalArgumentException ("Invalid string!");
      }
      val *= 10;
      val += s.charAt(i) - '0';
    }
    return (minus ? -val : val);
  }

  public static String intToStr(int val) {
    StringBuilder sb = new StringBuilder();
    boolean minus = false;
    if (val < 0) {
      minus = true;
      val *= -1;
    }
    while (val > 0) {
      sb.append(val % 10);
      val /= 10;
    }
    if (minus) {
      sb.append('-');
    }
    return sb.reverse().toString();
  }
}
