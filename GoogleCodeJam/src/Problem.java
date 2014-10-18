import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class Problem
{
	protected String problemName;
	
	public void solve() throws IOException
	{
		String[] inputs = new String[] { problemName + "-small", problemName + "-large" };
		for (String input : inputs)
		{
			System.out.println(input);
			FileWriter fw = new FileWriter(input + ".out");
			List<String> lines = Files.readAllLines(Paths.get(input + ".in"), Charset.defaultCharset());
			int N = Integer.parseInt(lines.get(0));
			for (int i = 0; i < N; ++i)
			{
				String output = solveProblemInstance(lines, i);
				System.out.print(output);
				fw.write(output);
			}
			fw.close();
			System.out.println();
		}
	}
	
	protected abstract String solveProblemInstance(List<String> lines, int i);
}
