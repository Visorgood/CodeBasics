import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class RopeIntranet {
  private final String problemName;
  
  public RopeIntranet() {
    this.problemName = "RopeIntranet";
  }

  public void solve() throws IOException {
    String[] inputs = new String[] {problemName + "-small", problemName + "-large"};
    for (String input : inputs) {
      System.out.println(input);
      FileWriter fw = new FileWriter(input + ".out");
      List<String> lines = Files.readAllLines(Paths.get(input + ".in"), Charset.defaultCharset());
      int T = Integer.parseInt(lines.get(0));
      int j = 1;
      for (int i = 0; i < T; ++i) {
        int N = Integer.parseInt(lines.get(j++));
        Rope[] ropes = new Rope[N];
        for (int k = 0; k < N; ++k) {
          String[] rope = lines.get(j++).split(" ");
          ropes[k] = new Rope(Integer.parseInt(rope[0]), Integer.parseInt(rope[1]));
        }
        String output = solveProblemInstanceBruteForce(ropes, i);
        System.out.print(output);
        fw.write(output);
      }
      fw.close();
      System.out.println();
    }
  }
  
  private String solveProblemInstanceBruteForce(Rope[] ropes, int i) {
    int n = 0;
    for (int j = 0; j < ropes.length; ++j) {
      Rope r1 = ropes[j];
      for (int k = j + 1; k < ropes.length; ++k) {
        Rope r2 = ropes[k];
        n += (r1.A > r2.A && r1.B < r2.B || r1.A < r2.A && r1.B > r2.B ? 1 : 0);
      }
    }
    return String.format("Case #%d: %d%n", i + 1, n);
  }
  
  private String solveProblemInstance(Rope[] ropes, int i) {
    int n = 0;
    // to devise and implement
    return String.format("Case #%d: %d%n", i + 1, n);
  }
}

class Rope {
  int A, B;
  
  Rope(int A, int B) {
    this.A = A;
    this.B = B;
  }
}

/*
 * 
Problem

A company is located in two very tall buildings.
The company intranet connecting the buildings consists of many wires, each connecting a window on the first building to a window on the second building.

You are looking at those buildings from the side, so that one of the buildings is to the left and one is to the right.
The windows on the left building are seen as points on its right wall, and the windows on the right building are seen as points on its left wall.
Wires are straight segments connecting a window on the left building to a window on the right building.

You've noticed that no two wires share an endpoint (in other words, there's at most one wire going out of each window).
However, from your viewpoint, some of the wires intersect midway.
You've also noticed that exactly two wires meet at each intersection point.

On the above picture, the intersection points are the black circles, while the windows are the white circles.

How many intersection points do you see?

Input

The first line of the input gives the number of test cases, T.
T test cases follow.
Each case begins with a line containing an integer N, denoting the number of wires you see.

The next N lines each describe one wire with two integers Ai and Bi.
These describe the windows that this wire connects: Ai is the height of the window on the left building, and Bi is the height of the window on the right building.

Output

For each test case, output one line containing "Case #x: y", where x is the case number (starting from 1) and y is the number of intersection points you see.

Limits

1 <= T <= 15.
1 <= Ai <= 104.
1 <= Bi <= 104.

Within each test case, all Ai are different.
Within each test case, all Bi are different.
No three wires intersect at the same point.

Small dataset

1 <= N <= 2.

Large dataset

1 <= N <= 1000.

Sample

Input 

2
3
1 10
5 5
7 7
2
1 1
2 2

Output

Case #1: 2
Case #2: 0

*/
