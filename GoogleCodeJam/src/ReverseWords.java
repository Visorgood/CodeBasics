import java.util.List;

public class ReverseWords extends Problem
{
	public ReverseWords()
	{
		this.problemName = "ReverseWords";
	}
	
	protected String solveProblemInstance(List<String> lines, int i)
	{
		String reversedLine = reverseLine(lines.get(1 + i));
		return String.format("Case #%d: %s%n", i + 1, reversedLine);
	}
	
	private String reverseLine(String line)
	{
		String[] words = line.split(" ");
		StringBuffer sb = new StringBuffer();
		for (int i = words.length - 1; i > 0; --i)
		{
			sb.append(words[i]);
			sb.append(" ");
		}
		sb.append(words[0]);
		return sb.toString();
	}
}

/*

Problem

Given a list of space separated words, reverse the order of the words.
Each line of text contains L letters and W words.
A line will only consist of letters and space characters.
There will be exactly one space character between each pair of consecutive words.

Input

The first line of input gives the number of cases, N.
N test cases follow.
For each test case there will a line of letters and space characters indicating a list of space separated words.
Spaces will not appear at the start or end of a line.

Output

For each test case, output one line containing "Case #x: " followed by the list of words in reverse order.

Limits

Small dataset

N = 5
1 <= L <= 25

Large dataset

N = 100
1 <= L <= 1000

Sample

Input 

3
this is a test
foobar
all your base

Output

Case #1: test a is this
Case #2: foobar
Case #3: base your all

*/
