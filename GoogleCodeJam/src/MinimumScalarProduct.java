import java.util.Arrays;
import java.util.List;


public class MinimumScalarProduct extends Problem
{
	public MinimumScalarProduct()
	{
		this.problemName = "MinimumScalarProduct";
	}
	
	protected String solveProblemInstance(List<String> lines, int i)
	{
		int n = Integer.parseInt(lines.get(1 + i * 3));
		String[] v1s = lines.get(1 + i * 3 + 1).split(" ");
		String[] v2s = lines.get(1 + i * 3 + 2).split(" ");
		long[] v1 = new long[n];
		long[] v2 = new long[n];
		for (int j = 0; j < n; ++j)
		{
			v1[j] = Long.parseLong(v1s[j]);
			v2[j] = Long.parseLong(v2s[j]);
		}
		long msp = computeMSP(n, v1, v2);
		return String.format("Case #%d: %d%n", i + 1, msp);
	}
	
	private long computeMSP(int n, long[] v1, long[] v2)
	{
		Arrays.sort(v1);
		Arrays.sort(v2);
		long msp = 0;
		for (int i = 0; i < n; ++i)
			msp += v1[i] * v2[n - (i + 1)];
		return msp;
	}
}

/*

Problem

You are given two vectors v1=(x1,x2,...,xn) and v2=(y1,y2,...,yn).
The scalar product of these vectors is a single number, calculated as x1y1+x2y2+...+xnyn.

Suppose you are allowed to permute the coordinates of each vector as you wish.
Choose two permutations such that the scalar product of your two new vectors is the smallest possible, and output that minimum scalar product.

Input

The first line of the input file contains integer number T - the number of test cases.
For each test case, the first line contains integer number n.
The next two lines contain n integers each, giving the coordinates of v1 and v2 respectively.

Output

For each test case, output a line

Case #X: Y
where X is the test case number, starting from 1, and Y is the minimum scalar product of all permutations of the two given vectors.

Limits

Small dataset

T = 1000
1 <= n <= 8
-1000 <= xi, yi <= 1000

Large dataset

T = 10
100 <= n <= 800
-100000 <= xi, yi <= 100000

Sample

Input
 
2
3
1 3 -5
-2 4 1
5
1 2 3 4 5
1 0 1 0 1

Output

Case #1: -25
Case #2: 6

*/
