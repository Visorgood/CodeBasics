import java.util.HashSet;

public class SplitWords {
  public static String execute(String str, HashSet<String> dict, int maxWordLen) {
    StringBuilder sb = new StringBuilder(str);
    return (executeRec(sb, 0, dict, maxWordLen) ? sb.toString() : null);
  }

  private static boolean executeRec(StringBuilder sb, int i, HashSet<String> dict, int maxWordLen) {
    if (i == sb.length()) {
      sb.delete(sb.length() - 1, sb.length());
      return true;
    }
    for (int j = i; j < sb.length() && j - i + 1 <= maxWordLen; ++j) {
      if (dict.contains(sb.substring(i, j + 1))) {
        sb.insert(j + 1, ' ');
        if (executeRec(sb, j + 2, dict, maxWordLen)) {
          return true;
        }
        sb.delete(j + 1, j + 2);
      }
    }
    return false;
  }
}
