public class BinarySearch {
  public static int execute(int[] array, int key) {
    if (array == null || array.length == 0) {
      return -1;
    }
    int l = 0;
    int r = array.length - 1;
    while (l <= r) {
      int m = (l + r) >> 1; // division by 2
      if (array[m] == key) {
        return m;
      } else if (array[m] > key) {
        r = m - 1;
      } else {
        l = m + 1;
      }
    }
    return -1;
  }
}
