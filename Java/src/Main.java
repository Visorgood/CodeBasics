import java.util.Random;

public class Main {
  static Random r = new Random();
  
  public static void main(String[] args) {
    //System.out.println(solution1("abc de fghij klm"));
    System.out.println(solution3(12, 57));
  }
  
  public static String solution1(String S) {
    StringBuilder res = new StringBuilder();
    String[] words = S.split(" ");
    for (String word : words) {
      int len = word.length();
      for (int i = 0; i < len; ++i) {
        res.append(word.charAt(len - i - 1));
      }
      res.append(" ");
    }
    return res.substring(0, res.length() - 1).toString();
  }
  
  public static int solution2(int[] A) {
    int n = A.length;
    boolean reversed = false;
    for (int i = 1; i < n - 1 && !reversed; ++i) {
      if (A[i - 1] == A[i + 1] && A[i - 1] != A[i]) {
        A[i] = A[i - 1]; 
        reversed = true;
      }
    }
    if (!reversed) {
      if (A[0] != A[1]) {
        A[0] = A[1];
      } else {
        A[n - 1] = A[n - 2];
      }
    }
    int adjacency = 0;
    for (int i = 1; i < n; ++i) {
      if (A[i - 1] == A[i]) {
        ++adjacency;
      }
    }
    return adjacency;
  }
  
  public static int solution3(int A, int B) {
    int maxResult = 100000000;
    int result = 0;
    if (A == 0 && B == 0) {
      return 0;
    }
    if (A == 0) {
      return B;
    }
    if (B == 0) {
      int t = (int) Math.pow(10, (int) Math.log10(A));
      int p = (A / t) * t;
      result = p * 10 + (A - p);
      return (result <= maxResult ? result : -1);
    }
    int n = (int) Math.log10(A) + 1;
    int m = (int) Math.log10(B) + 1;
    int divA = (int) Math.pow(10, n - 1);
    int divB = (int) Math.pow(10, m - 1);
    while (divA > 0 || divB > 0) {
      if (divA > 0) {
        int tA = A / divA;
        result = result * 10 + tA;
        A -= tA * divA;
        divA /= 10;
      }
      if (divB > 0) {
        int tB = B / divB;
        result = result * 10 + tB;
        B -= tB * divB;
        divB /= 10;
      }
    }
    return (result <= maxResult ? result : -1);
  }
}
