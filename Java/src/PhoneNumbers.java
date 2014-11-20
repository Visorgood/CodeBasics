import java.util.Iterator;
import java.util.LinkedList;

public class PhoneNumbers {
  public static Iterator<String> generate(String number) {
    if (number == null)
      return null;
    LinkedList<String> list = new LinkedList<String>();
    if (number.length() == 0)
      return list.iterator();
    StringBuilder sb = new StringBuilder();
    generateRec(number, sb, list);
    return list.iterator();
  }

  private static void generateRec(String number, StringBuilder sb, LinkedList<String> list) {
    if (sb.length() == number.length()) {
      list.add(sb.toString());
      return;
    }
    char[] chars = digitToChars(number.charAt(sb.length()));
    for (char c : chars) {
      sb.append(c);
      generateRec(number, sb, list);
      sb.setLength(sb.length() - 1);
    }
  }

  private static char[] digitToChars(char digit) {
    switch (digit) {
      case '2':
        return new char[] {'a', 'b', 'c'};
      case '3':
        return new char[] {'d', 'e', 'f'};
      case '4':
        return new char[] {'g', 'h', 'i'};
      case '5':
        return new char[] {'j', 'k', 'l'};
      case '6':
        return new char[] {'m', 'n', 'o'};
      case '7':
        return new char[] {'p', 'q', 'r', 's'};
      case '8':
        return new char[] {'t', 'u', 'v'};
      case '9':
        return new char[] {'w', 'x', 'y', 'z'};
      default:
        return new char[] {'-'};
    }
  }
}
