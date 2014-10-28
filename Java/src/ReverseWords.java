public class ReverseWords {
  public static String execute(String str) {
    if (str == null)
      return null;
    char[] newStr = new char[str.length()];
    int j = 0;
    for (int i = str.length() - 1; i >= 0; --i) {
      if (str.charAt(i) == ' ') {
        j = moveWord(str, newStr, i + 1, j);
        newStr[j++] = ' ';
      }
    }
    moveWord(str, newStr, 0, j);
    return new String(newStr);
  }
  
  private static int moveWord(String str, char[] newStr, int i, int j) {
    for (int k = i; k < str.length(); ++k) {
      char c = str.charAt(k);
      if (c == ' ') {
        break;
      }
      newStr[j++] = c;
    }
    return j;
  }
}
