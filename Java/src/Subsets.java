import java.util.LinkedList;
import java.util.Iterator;

public class Subsets {
  public static Iterator<String> generate(String str) {
    if (str == null) {
      return null;
    }
    LinkedList<String> subsets = new LinkedList<String>();
    StringBuilder sb = new StringBuilder();
    generate(str, 0, sb, subsets);
    return subsets.iterator();
  }
  
  public static void generate(String str, int i, StringBuilder sb, LinkedList<String> subsets) {
    subsets.addLast(sb.toString());
    if (i == str.length()) {
      return;
    }
    for (int j = i; j < str.length(); ++j) {
      sb.append(str.charAt(j));
      generate(str, j + 1, sb, subsets);
      sb.setLength(sb.length() - 1);
    }
  }
  
  public static void print(String str) {
    if (str == null) {
      return;
    }
    StringBuilder sb = new StringBuilder();
    print(str, 0, sb);
  }

  private static void print(String str, int i, StringBuilder sb) {
    System.out.println(sb.toString());
    if (i == str.length()) {
      return;
    }
    for (int j = i; j < str.length(); ++j) {
      sb.append(str.charAt(j));
      print(str, j + 1, sb);
      sb.setLength(sb.length() - 1);
    }
  }
}
