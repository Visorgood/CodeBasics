import java.util.HashMap;
import java.util.HashSet;

public class MinWindow {
  public static Window computeRecursively(String s, char[] chars) {
    HashSet<Character> charSet = new HashSet<Character>();
    for (char c : chars) {
      charSet.add(c);
    }
    Window w = new Window(0, s.length() - 1);
    return (computeRecursively(s, w, charSet, chars) ? w : null);
  }

  private static boolean computeRecursively(String s, Window w, HashSet<Character> charSet,
      char[] chars) {
    if (!isGoodWindow(s, w, chars)) {
      return false;
    }
    Window newW1 = new Window(w.L + 1, w.R);
    while (newW1.R - newW1.L + 1 > charSet.size() && !charSet.contains(s.charAt(newW1.L))) {
      ++newW1.L;
    }
    if (!computeRecursively(s, newW1, charSet, chars)) {
      newW1 = w;
    }
    Window newW2 = new Window(w.L, w.R - 1);
    while (newW2.R - newW2.L + 1 > charSet.size() && !charSet.contains(s.charAt(newW2.R))) {
      --newW2.R;
    }
    if (!computeRecursively(s, newW2, charSet, chars)) {
      newW2 = w;
    }
    if (newW1.R - newW1.L <= newW2.R - newW2.L) {
      w.L = newW1.L;
      w.R = newW1.R;
    } else {
      w.L = newW2.L;
      w.R = newW2.R;
    }
    return true;
  }

  private static boolean isGoodWindow(String s, Window w, char[] chars) {
    if (w.L < 0 || w.R >= s.length() || w.R - w.L + 1 < chars.length) {
      return false;
    }
    s = s.substring(w.L, w.R + 1);
    for (char c : chars) {
      if (!s.contains(String.valueOf(c))) {
        return false;
      }
    }
    return true;
  }

  public static Window computeLinearly(String s, char[] chars) {
    HashSet<Character> charSet = new HashSet<Character>();
    for (char c : chars) {
      charSet.add(c);
    }
    HashMap<Character, Integer> frequencies = new HashMap<Character, Integer>();
    int n = s.length();
    int m = chars.length;
    Window minW = new Window(-1, -1);
    Window w = new Window(0, 0);
    while (w.R < n) {
      while (w.R < n  && frequencies.size() < m) {
        char c = s.charAt(w.R);
        if (charSet.contains(c)) {
          Integer freq = frequencies.get(c);
          frequencies.put(c, (freq != null ? freq.intValue() : 0) + 1);
        }
        ++w.R;
      }
      if (frequencies.size() == m && (minW.L == -1 && minW.R == -1 || w.R - w.L - 1 < minW.R - minW.L)) {
        minW.L = w.L;
        minW.R = w.R - 1;
      }
      while (w.L < w.R && frequencies.size() == m) {
        char c = s.charAt(w.L);
        if (charSet.contains(c)) {
          int freq = frequencies.get(c);
          if (freq == 1) {
            frequencies.remove(c);
            if (w.R - w.L - 1 < minW.R - minW.L) {
              minW.L = w.L;
              minW.R = w.R - 1;
            }
          } else {
            frequencies.put(c, freq - 1);
          }
        }
        ++w.L;
      }
    }
    return minW;
  }
}


class Window {
  public int L, R;

  public Window(int L, int R) {
    this.L = L;
    this.R = R;
  }
  
  public String toString() {
    return L + " " + R;
  }
}
