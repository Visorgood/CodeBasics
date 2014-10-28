public class LinkedListCycle {
  public static class Node {
    public int val;
    public Node next;
    
    public Node (int val, Node next) {
      this.val = val;
      this.next = next;
    }
  }
  
  public static Node findStartOfCycle(Node head) {
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

    int cycleLength = 1;
    slow = slow.next;
    while (slow != fast) {
      slow = slow.next;
      ++cycleLength;
    }

    slow = head;
    fast = head;

    while (--cycleLength >= 0)
      fast = fast.next;

    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }

    return slow;
  }
}
