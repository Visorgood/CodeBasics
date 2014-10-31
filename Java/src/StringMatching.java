public class StringMatching {
  public static int naive(String T, String P) {
    int n = T.length();
    int m = P.length();
    for (int i = 0; i < n - m + 1; ++i) {
      boolean p = true;
      for (int j = 0; j < m && p; ++j) {
        if (T.charAt(i + j) != P.charAt(j)) {
          p = false;
        }
      }
      if (p) {
        return i;
      }
    }
    return -1;
  }
  
  public static int naiveUsingPrefixTree(String T, String P) {
    int n = T.length();
    int m = P.length();
    PrefixTree trie = new PrefixTree();
    for (int i = 0; i < n - m + 1; ++i) {
      trie.insert(T.substring(i, i + m), i);
      int index = trie.get(P);
      if (index > -1) {
        return index;
      }
    }
    return -1;
  }
}
