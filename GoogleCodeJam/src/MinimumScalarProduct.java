import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class MinimumScalarProduct
{
	public void execute() throws IOException
	{
		String problemName = "MinimumScalarProduct";
		String[] inputs = new String[] { problemName + "-small", problemName + "-large" };
		for (String input : inputs)
		{
			System.out.println(input);
			FileWriter fw = new FileWriter(input + ".out");
			List<String> lines = Files.readAllLines(Paths.get(input + ".in"), Charset.defaultCharset());
			int N = Integer.parseInt(lines.get(0));
			for (int i = 0; i < N; ++i)
			{
				lines.get(1 + i);
				String output = String.format("Case #%d: \n", i + 1);
				System.out.print(output);
				fw.write(output);
			}
			fw.close();
			System.out.println();
		}
	}
}
