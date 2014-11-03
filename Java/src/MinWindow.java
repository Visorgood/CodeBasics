import java.util.HashSet;

public class MinWindow {
  public static Window compute(String s, char[] chars) {
    HashSet<Character> charSet = new HashSet<Character>();
    for (char c : chars) {
      charSet.add(c);
    }
    Window w = new Window(0, s.length() - 1);
    return (compute(s, w, charSet, chars) ? w : null);
  }

  private static boolean compute(String s, Window w, HashSet<Character> charSet, char[] chars) {
    if (!isGoodWindow(s, w, chars)) {
      return false;
    }
    Window newW1 = new Window(w.L + 1, w.R);
    while (newW1.R - newW1.L + 1 > charSet.size() && !charSet.contains(s.charAt(newW1.L))) {
      ++newW1.L;
    }
    if (!compute(s, newW1, charSet, chars)) {
      newW1 = w;
    }
    Window newW2 = new Window(w.L, w.R - 1);
    while (newW2.R - newW2.L + 1 > charSet.size() && !charSet.contains(s.charAt(newW2.R))) {
      --newW2.R;
    }
    if (!compute(s, newW2, charSet, chars)) {
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
    s = s.substring(w.L, w.R + 1);
    for (char c : chars) {
      if (!s.contains(String.valueOf(c))) {
        return false;
      }
    }
    return true;
  }
}

class Window {
  public int L, R;

  public Window(int L, int R) {
    this.L = L;
    this.R = R;
  }
}
