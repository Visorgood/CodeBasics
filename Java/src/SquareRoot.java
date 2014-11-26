public class SquareRoot {
  public static double compute(double x, double e) {
    if (x < 0.0) {
      throw new IllegalArgumentException("x < 0");
    }
    if (Double.compare(x, 0.0) == 0) {
      return 0.0;
    }
    if (Double.compare(x, 1.0) == 0) {
      return 1.0;
    }
    double l = (Double.compare(x, 1.0) < 0 ? x : 1.0);
    double r = (Double.compare(x, 1.0) < 0 ? 1.0 : x);
    double sqrtx = 0.0;
    double y = 0.0;
    while (Math.abs(y - x) > e) {
      sqrtx = l + (r - l) * 0.5;
      y = sqrtx * sqrtx;
      if (y > x) {
        r = sqrtx;
      } else if (y < x) {
        l = sqrtx;
      }
    }
    return sqrtx;
  }
}
