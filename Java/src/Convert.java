public class Convert {
  public static int strToInt(String s) throws IllegalArgumentException  {
    if (s == null || s.length() == 0) {
      throw new IllegalArgumentException("Input string is null or has the length 0!");
    }
    boolean minus = false;
    if (s.charAt(0) == '-') {
      minus = true;
      if (s.length() == 1) {
        throw new IllegalArgumentException("Input string contains a single character '-'!");
      }
    }
    long val = 0;
    for (int i = (minus ? 1 : 0); i < s.length(); ++i) {
      char c = s.charAt(i);
      if (!Character.isDigit(c)) {
        throw new IllegalArgumentException("Input string has invalid characters!");
      }
      val = val * 10 + (s.charAt(i) - '0');
      if (val > Integer.MAX_VALUE) {
        throw new IllegalArgumentException("Integer overflow!");
      }
    }
    return (int)(minus ? -val : val);
  }

  public static String intToStr(int val) {
    if (val == 0) {
      return "0";
    }
    StringBuilder sb = new StringBuilder();
    boolean minus = false;
    if (val < 0) {
      val = -val;
      minus = true;
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
