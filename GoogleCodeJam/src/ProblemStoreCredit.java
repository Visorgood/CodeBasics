import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class ProblemStoreCredit
{
	public void execute() throws IOException
	{
		List<String> lines = Files.readAllLines(Paths.get("ProblemStoreCredit-large.in"), Charset.defaultCharset());
		int numberOfInputs = Integer.parseInt(lines.get(0));
		for (int i = 0; i < numberOfInputs; ++i)
		{
			int C = Integer.parseInt(lines.get(1 + i * 3));
			int I = Integer.parseInt(lines.get(1 + i * 3 + 1));
			String[] Ls = lines.get(1 + i * 3 + 2).split(" ");
			int[] L = new int[I];
			for (int j = 0; j < I; ++j)
				L[j] = Integer.parseInt(Ls[j]);
			Indexes indexes = computeIndexes(C, I, L);
			if (indexes != null)
			{
				System.out.format("Case #%d: %d %d", i + 1, indexes.i, indexes.j);
				System.out.println();
			}
		}
	}
	
	private Indexes computeIndexes(int C, int I, int[] L)
	{
		HashMap<Integer, Integer> values = new HashMap<Integer, Integer>();
		for (int i = 0; i < I; ++i)
			values.put(L[i], i);
		
		for (int i = 0; i < I; ++i)
		{
			Integer j = values.get(C - L[i]);
			if (j != null && j != i)
				return (i < j ? new Indexes(i + 1, j + 1) : new Indexes(j + 1, i + 1));
		}
		
		return null;
	}
	
	private class Indexes
	{
		int i;
		int j;
		
		public Indexes(int i, int j)
		{
			this.i = i;
			this.j = j;
		}
	}
}

/*

Problem

You receive a credit C at a local store and would like to buy two items.
You first walk through the store and create a list L of all available items.
From this list you would like to buy two items that add up to the entire value of the credit.
The solution you provide will consist of the two integers indicating the positions of the items in your list (smaller number first).

Input

The first line of input gives the number of cases, N.
N test cases follow. For each test case there will be:

One line containing the value C, the amount of credit you have at the store.
One line containing the value I, the number of items in the store.
One line containing a space separated list of I integers. Each integer P indicates the price of an item in the store.
Each test case will have exactly one solution.

Output

For each test case, output one line containing "Case #x: " followed by the indices of the two items whose price adds up to the store credit.
The lower index should be output first.

Limits

5 <= C <= 1000
1 <= P <= 1000

Small dataset

N = 10
3 <= I <= 100

Large dataset

N = 50
3 <= I <= 2000

Sample

Input

3
100
3
5 75 25
200
7
150 24 79 50 88 345 3
8
8
2 1 9 4 4 56 90 3

Output

Case #1: 2 3
Case #2: 1 4
Case #3: 4 5

*/
