import java.util.Iterator;
import java.util.LinkedList;

public class Permutations {
  public static Iterator<String> generate(String str) {
    if (str == null) {
      return null;
    }
    LinkedList<String> permutations = new LinkedList<String>();
    if (str.length() > 0) {
      StringBuilder sb = new StringBuilder();
      boolean[] added = new boolean[str.length()];
      generate(str, 0, sb, added, permutations);
    }
    return permutations.iterator();
  }

  private static void generate(String str, int firstNotAdded, StringBuilder sb, boolean[] added, LinkedList<String> permutations) {
    if (sb.length() == str.length()) {
      permutations.addLast(sb.toString());
      return;
    }
    for (int j = firstNotAdded; j < str.length(); ++j) {
      if (!added[j]) {
        added[j] = true;
        sb.append(str.charAt(j));
        generate(str, (j == firstNotAdded ? j + 1 : firstNotAdded), sb, added, permutations);
        sb.setLength(sb.length() - 1);
        added[j] = false;
      }
    }
  }

  public static void print(String str) {
    if (str == null || str.length() == 0) {
      return;
    }
    StringBuilder sb = new StringBuilder();
    boolean[] added = new boolean[str.length()];
    print(str, 0, sb, added);
  }

  private static void print(String str, int firstNotAdded, StringBuilder sb, boolean[] added) {
    if (sb.length() == str.length()) {
      System.out.println(sb.toString());
      return;
    }
    for (int j = firstNotAdded; j < str.length(); ++j) {
      if (!added[j]) {
        sb.append(str.charAt(j));
        added[j] = true;
        print(str, (j == firstNotAdded ? j + 1 : firstNotAdded), sb, added);
        sb.setLength(sb.length() - 1);
        added[j] = false;
      }
    }
  }
/*
  private final String str;
  private final int[] indexes;
  private boolean hasNext;

  public Permutations(String str) {
    if (str == null)
      throw new NullPointerException("str == null");
    this.str = str;
    this.indexes = new int[str.length()];
    for (int i = 0; i < indexes.length; ++i) {
      this.indexes[i] = i;
    }
    this.hasNext = (str.length() > 0);
  }

  public boolean hasNext() {
    return hasNext;
  }

  public String next() {
    if (!hasNext) {
      return null;
    }
    String currentString = buildString();
    hasNext = findNext();
    return currentString;
  }
  
  private String buildString() {
    StringBuilder sb = new StringBuilder();
    for (int i : indexes) {
      sb.append(str.charAt(i));
    }
    return sb.toString();
  }
  
  private boolean findNext() {
    boolean p = false;
    for (int i = indexes.length - 2; i >= 0 && !p; --i) {
      int min = indexes[i + 1];
      for (int j = i + 2; j < indexes.length; ++i) {
        if (indexes[j] < min) {
          min = indexes[j];
        }
      }
      indexes[i] = min;
    }
      if (isAllowed(indexes[i] + 1)) {
        ++indexes[i];
        for (int j = i + 1; j < indexes.length; ++i) {
          if (isAllowed(indexes[i] + )) {
        }
        break;
      }
    }
    return true;
  }
  */
}
