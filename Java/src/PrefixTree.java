import java.util.HashMap;

public class PrefixTree {
  private final Node root;

  public PrefixTree() {
    root = new Node("", null);
  }

  public void insert(String key) {
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
    node.isKey = true;
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
    node.isKey = false;
    while (node.children.size() == 0 && !node.isKey) {
      String symbol = node.symbol;
      node = node.parent;
      node.children.remove(symbol);
    }
  }

  public boolean exists(String key) {
    if (key == null || key.length() == 0) {
      return false;
    }
    Node node = root;
    for (int i = 0; i < key.length(); ++i) {
      String symbol = key.substring(i, i + 1);
      node = node.children.get(symbol);
      if (node == null) {
        return false;
      }
    }
    return node.isKey;
  }

  private class Node {
    String symbol;
    boolean isKey;
    Node parent;
    HashMap<String, Node> children;

    Node(String symbol, Node parent) {
      this.symbol = symbol;
      this.isKey = false;
      this.parent = parent;
      this.children = new HashMap<String, Node>();
    }
  }
}
