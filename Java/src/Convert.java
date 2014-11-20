public class Convert {
  public static int strToInt(String s) throws IllegalArgumentException {
    return strToInt(s, 10);
  }

  public static int strToInt(String s, int base) throws IllegalArgumentException {
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
      if (!isProperDigit(c, base)) {
        throw new IllegalArgumentException("Input string has invalid characters!");
      }
      val = val * base + (Character.isDigit(c) ? s.charAt(i) - '0' : s.charAt(i) - 'A' + 10);
      if (val > Integer.MAX_VALUE) {
        throw new IllegalArgumentException("Integer overflow!");
      }
    }
    return (int) (minus ? -val : val);
  }

  public static String intToStr(int val) {
    return intToStr(val, 10);
  }

  public static String intToStr(int val, int base) {
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
      int digit = val % base;
      sb.append((char)(digit < 10 ? '0' + digit : 'A' + digit - 10));
      val /= base;
    }
    if (minus) {
      sb.append('-');
    }
    return sb.reverse().toString();
  }

  public static String toBase(String s, int b1, int b2) {
    int val = strToInt(s, b1);
    return intToStr(val, b2);
  }

  private static boolean isProperDigit(char c, int base) {
    return (base <= 10 ? Character.isDigit(c) && (c - '0' < base) : Character.isDigit(c)
        || (c - 'A' + 10 < base));
  }
}
