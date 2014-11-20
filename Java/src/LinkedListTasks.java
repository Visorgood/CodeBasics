public class LinkedListTasks {
  public static class Node {
    public int val;
    public Node next;
    
    public Node (int val, Node next) {
      this.val = val;
      this.next = next;
    }
  }
  
  public static Node reverse(Node head, boolean recursive) {
    return (recursive ? reverseRec(head) : reverseNoRec(head));
  }

  private static Node reverseRec(Node head) {
    if (head == null || head.next == null) {
      return head;
    }
    Node newHead = reverseRec(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
  }
  
  private static Node reverseNoRec(Node head) {
    Node prev = null;
    Node curr = head;
    while (curr != null) {
      Node temp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = temp;
    }
    return prev;
  }
  
  public static Node findCycleStart(Node head) {
    if (head == null)
      return null;

    Node slow = head;
    Node fast = head.next;
    while (slow != fast) {
      if (fast == null || fast.next == null)
        return null;
      slow = slow.next;
      fast = fast.next.next;
    }

    slow = head;
    fast = fast.next;

    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }

    return slow;
  }
  
  public static void print(Node head) {
    Node node = head;
    while (node != null) {
      System.out.print(node.val + " -> ");
      node = node.next;
    }
    System.out.println("null");
  }
}
