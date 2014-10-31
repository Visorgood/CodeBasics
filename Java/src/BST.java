class BST<T extends Comparable<T>> {
  Node root;

  public BST() {
    this.root = null;
  }

  public boolean search(T key) {
    return (null != searchNode(key));
  }

  private Node searchNode(T key) {
    if (null == key) {
      return null;
    }
    Node node = this.root;
    while (null != node) {
      int compare = node.key.compareTo(key);
      if (0 == compare) {
        return node;
      }
      node = (1 == compare ? node.left : node.right);
    }
    return null;
  }

  public void insert(T key) {
    if (null == key) {
      return;
    }
    if (null == this.root) {
      this.root = new Node(key);
      return;
    }
    Node node = this.root;
    while (true) {
      ++node.size;
      int compare = node.key.compareTo(key);
      if (compare >= 0) {
        if (null == node.left) {
          node.left = new Node(key);
          node.left.parent = node;
          return;
        }
        node = node.left;
      } else {
        if (null == node.right) {
          node.right = new Node(key);
          node.right.parent = node;
          return;
        }
        node = node.right;
      }
    }
  }

  public T min() {
    return min(this.root);
  }

  private T min(Node node) {
    if (null == node) {
      return null;
    }
    while (null != node.left) {
      node = node.left;
    }
    return node.key;
  }

  public T max() {
    return max(this.root);
  }

  private T max(Node node) {
    if (null == node) {
      return null;
    }
    while (null != node.right) {
      node = node.right;
    }
    return node.key;
  }

  public T predecessor(T key) {
    Node node = searchNode(key);
    if (null == node) {
      return null;
    }
    if (null != node.left) {
      return max(node.left);
    }
    while (null != node && !(node.key.compareTo(key) < 0)) {
      node = node.parent;
    }
    return (null != node ? node.key : null);
  }

  public T successor(T key) {
    Node node = searchNode(key);
    if (null == node) {
      return null;
    }
    if (null != node.right) {
      return min(node.right);
    }
    while (null != node && !(node.key.compareTo(key) > 0)) {
      node = node.parent;
    }
    return (null != node ? node.key : null);
  }

  public T[] getSorted() {
    @SuppressWarnings("unchecked")
    T[] array = (T[]) new Object[size()];
    getSorted(array, 0, this.root);
    return array;
  }

  private int getSorted(T[] array, int i, Node node) {
    if (null == node)
      return i;
    i = getSorted(array, i, node.left);
    array[i++] = node.key;
    i = getSorted(array, i, node.right);
    return i;
  }

  /*
   * public void delete(T key) { Node node = null; while (null != (node = SearchNode(value))) { if
   * (null == node.left && null == node.right) { if (node == this.root) this.root = null; else {
   * Node parent = node.parent; if (node == parent.left) parent.left = null; else parent.right =
   * null; while (null != parent) { --parent.size; parent = parent.parent; } } } else if ((null ==
   * node.left) ^ (null == node.right)) { Node child = (null == node.left ? node.right : node.left);
   * if (node == this.root) { this.root = child; this.root.parent = null; } else { Node parent =
   * node.parent; if (node == parent.left) parent.left = child; else parent.right = child;
   * child.parent = parent; while (null != parent) { --parent.size; parent = parent.parent; } } }
   * else { throw new NotImplementedException(); Node pred = node.left; while (null != pred.right)
   * pred = pred.right; Node predParent = pred.parent; if (pred != node.left) { if (null !=
   * pred.left) pred.left.parent = pred.parent; pred.parent.right = pred.left; } Node parent =
   * node.parent; if (node == parent.left) parent.left = pred; else parent.right = pred; pred.parent
   * = parent; if (pred != node.left) pred.left = node.left; else predParent = pred; pred.right =
   * node.right; while (null != predParent) { --predParent.size; predParent = predParent.parent; } }
   * } }
   */

  public T select(int i) {
    if (i <= 0 || i > size()) {
      return null;
    }
    Node node = this.root;
    while (true) {
      int sizeLeft = (null != node.left ? node.left.size : 0);
      if (sizeLeft == i - 1) {
        return node.key;
      }
      if (sizeLeft >= i) {
        node = node.left;
      } else {
        node = node.right;
        i -= (sizeLeft + 1);
      }
    }
  }

  public int rank(T key) {
    Node node = searchNode(key);
    if (null == node) {
      return 0;
    }
    int rank = 0;
    while (null != node) {
      if (node.key.compareTo(key) < 0) {
        rank += (null != node.left ? node.left.size : 0) + 1;
      }
      node = node.parent;
    }
    return rank;
  }

  public int size() {
    return (null != this.root ? this.root.size : 0);
  }

  private class Node {
    public final T key;
    public int size;
    public Node parent, left, right;

    public Node(T key) {
      this.key = key;
      this.size = 1;
      this.parent = null;
      this.left = null;
      this.right = null;
    }
  }
}
