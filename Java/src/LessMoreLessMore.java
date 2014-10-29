import java.util.Arrays;

public class LessMoreLessMore {
  public static void sort(int[] array) {
    Arrays.sort(array);
    int n = array.length;
    int p = (n % 2 == 0 ? 0 : 1);
    for (int i = 1; i <= n / 2; i += 2) {
      int t = array[i];
      array[i] = array[n - (i + 1) + p];
      array[n - (i + 1) + p] = t;
    }
  }
}
