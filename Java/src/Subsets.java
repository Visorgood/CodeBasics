import java.util.ArrayList;
import java.util.Iterator;

public class Subsets
{
	public static Iterator<String> generate(String str)	{
		ArrayList<String> subsets = new ArrayList<String>();
		
		if (str == null || str.length() == 0)
			return subsets.iterator();
		
		subsets.add("");
		for (char c : str.toCharArray()) {
			int n = subsets.size();
			for (int i = 0; i < n; ++i)
				subsets.add(subsets.get(i) + c);
		}
		
		return subsets.iterator();
	}
}
