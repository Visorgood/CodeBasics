import java.util.Iterator;
import java.util.LinkedList;

public class PhoneNumbers {
  public static Iterator<String> generate(String number) {
    if (number == null)
      return null;
    LinkedList<String> list = new LinkedList<String>();
    if (number.length() == 0)
      return list.iterator();
    generateRec(number, 0, "", list);
    return list.iterator();
  }

  private static void generateRec(String number, int i, String s, LinkedList<String> list) {
    if (i == number.length()) {
      list.add(s);
      return;
    }
    char[] chars = digitToChars(number.charAt(i));
    for (char c : chars) {
      generateRec(number, i + 1, s + c, list);
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
