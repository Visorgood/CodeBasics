class Equilibrium {
  public static int find(int[] A) {
    int N = A.length;
    long[] sumLeft = new long[N];
    long[] sumRight = new long[N];
    for (int i = 0; i < N; ++i) {
      sumLeft[i] = (i > 0 ? sumLeft[i - 1] + A[i - 1] : 0);
      sumRight[N - i - 1] = (i > 0 ? sumRight[N - i] + A[N - i] : 0);
    }
    for (int i = 0; i < N; ++i) {
      if (sumLeft[i] == sumRight[i]) {
        return i;
      }
    }
    return -1;
  }

  public static int tape(int[] A) {
    int N = A.length;
    long[] sumLeft = new long[N];
    long[] sumRight = new long[N];
    for (int i = 0; i < N; ++i) {
      sumLeft[i] = (i > 0 ? sumLeft[i - 1] + A[i - 1] : 0);
      sumRight[N - i - 1] = (i > 0 ? sumRight[N - i] + A[N - i - 1] : A[N - i - 1]);
    }
    long min = Long.MAX_VALUE;
    for (int i = 1; i < N; ++i) {
      long currMin = Math.abs(sumLeft[i] - sumRight[i]);
      if (currMin < min) {
        min = currMin;
      }
    }
    return (int) min;
  }
}
