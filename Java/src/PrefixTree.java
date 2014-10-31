import java.util.HashMap;

// only non-negative values are allowed
// -1 is reserved as a flag that node is not a key node
public class PrefixTree {
  private final Node root;

  public PrefixTree() {
    root = new Node("", null);
  }

  public void insert(String key, int value) {
    if (key == null || key.length() == 0) {
      return;
    }
    Node node = root;
    for (int i = 0; i < key.length(); ++i) {
      String symbol = key.substring(i, i + 1);
      if (!node.children.containsKey(symbol)) {
        node.children.put(symbol, new Node(symbol, node));
      }
      node = node.children.get(symbol);
    }
    node.value = value;
  }

  public void delete(String key) {
    if (key == null || key.length() == 0) {
      return;
    }
    Node node = root;
    for (int i = 0; i < key.length(); ++i) {
      String symbol = key.substring(i, i + 1);
      node = node.children.get(symbol);
      if (node == null) {
        return;
      }
    }
    node.value = -1;
    while (node.children.size() == 0 && node.value == -1) {
      String symbol = node.symbol;
      node = node.parent;
      node.children.remove(symbol);
    }
  }

  public int get(String key) {
    if (key == null || key.length() == 0) {
      return -1;
    }
    Node node = root;
    for (int i = 0; i < key.length(); ++i) {
      String symbol = key.substring(i, i + 1);
      node = node.children.get(symbol);
      if (node == null) {
        return -1;
      }
    }
    return node.value;
  }

  private class Node {
    String symbol;
    int value;
    Node parent;
    HashMap<String, Node> children;

    Node(String symbol, Node parent) {
      this.symbol = symbol;
      this.value = -1;
      this.parent = parent;
      this.children = new HashMap<String, Node>();
    }
  }
}
