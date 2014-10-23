import java.util.Iterator;
import java.util.LinkedList;

public class Permutations {
	public static Iterator<String> generate(String str) {
		LinkedList<String> queue = new LinkedList<String>();
		
		if (str == null || str.length() == 0)
			return queue.iterator();
		
		queue.addFirst(str.substring(0, 1));
		long k1f = 1;
		for (int i = 1; i < str.length(); ++i) {
			char c = str.charAt(i);
			k1f *= (i + 1);
			while (queue.size() < k1f) {
				String perm = queue.pollLast();
				for (int j = 0; j <= perm.length(); ++j)
					queue.addFirst(perm.substring(0, j) + c + perm.substring(j));
			}
		}
		
		return queue.iterator();
	}
}
