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
    Node minNode = findMinNode(this.root);
    return (minNode != null ? minNode.key : null);
  }

  private Node findMinNode(Node node) {
    if (null == node) {
      return null;
    }
    while (null != node.left) {
      node = node.left;
    }
    return node;
  }

  public T max() {
    Node maxNode = findMaxNode(this.root);
    return (maxNode != null ? maxNode.key : null);
  }

  private Node findMaxNode(Node node) {
    if (null == node) {
      return null;
    }
    while (null != node.right) {
      node = node.right;
    }
    return node;
  }

  public T predecessor(T key) {
    Node node = searchNode(key);
    if (null == node) {
      return null;
    }
    if (null != node.left) {
      return findMaxNode(node.left).key;
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
      return findMinNode(node.right).key;
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

  public void delete(T key) {
    Node node = null;
    while (null != (node = searchNode(key))) {
      if (null == node.left && null == node.right) {
        deleteWithNoChildNodes(node);
      } else if ((null == node.left) ^ (null == node.right)) {
        deleteWithOneChildNode(node);
      } else {
        deleteWithTwoChildNodes(node);
      }
    }
  }

  private void deleteWithNoChildNodes(Node node) {
    if (node == this.root) {
      this.root = null;
    } else {
      Node parent = node.parent;
      if (node == parent.left) {
        parent.left = null;
      } else {
        parent.right = null;
      }
      node.parent = null;
      while (null != parent) {
        --parent.size;
        parent = parent.parent;
      }
    }
  }

  private void deleteWithOneChildNode(Node node) {
    Node child = (null != node.left ? node.left : node.right);
    if (node == this.root) {
      this.root = child;
      this.root.parent = null;
    } else {
      Node parent = node.parent;
      if (node == parent.left) {
        parent.left = child;
      } else {
        parent.right = child;
      }
      node.parent = null;
      child.parent = parent;
      while (null != parent) {
        --parent.size;
        parent = parent.parent;
      }
    }
  }

  private void deleteWithTwoChildNodes(Node node) {
    Node pred = node.left;
    while (null != pred.right) {
      pred = pred.right;
    }
    swap(node, pred);
    if (null == node.left && null == node.right) {
      deleteWithNoChildNodes(node);
    } else {
      deleteWithOneChildNode(node);
    }
  }

  private void swap(Node node1, Node node2) {
    Node node1Parent = node1.parent;
    if (null != node1Parent) {
      if (node1 == node1Parent.left) {
        node1Parent.left = node2;
      } else {
        node1Parent.right = node2;
      }
    }
    Node node2Parent = node2.parent;
    if (null != node2Parent) {
      if (node2 == node2Parent.left) {
        node2Parent.left = node1;
      } else {
        node2Parent.right = node1;
      }
    }
    node2.parent = node1Parent;
    node1.parent = node2Parent;
    Node t = node1.left;
    node1.left = node2.left;
    node2.left = t;
    node1.left.parent = node2;
    node2.left.parent = node1;
    t = node1.right;
    node1.right = node2.right;
    node2.right = t;
    node1.right.parent = node2;
    node2.right.parent = node1;
  }

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
