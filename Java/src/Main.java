import java.io.IOException;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Random;

public class Main
{
	private static Boolean allCharsUniq(String str)
	{
		BitSet bs = new BitSet(256);
		for (char c : str.toCharArray())
		{
			if (bs.get(c))
				return false;
			bs.set(c);
		}
		return true;
	}

	
	public static void main(String[] args) throws IOException
	{
		//useSort(new MergeSort());
		//useSort(new QuickSort());
		//System.out.println(allCharsUniq("abcdefgh"));
		//Iterator<String> it = Subsets.generate("abcde");
		//Iterator<String> it = Permutations.generate("abcde");
		Iterator<String> it = PhoneNumberStringPermutations.generate("0123");
		while (it.hasNext())
			System.out.println(it.next());
	}
	
	static void useSort(Sort sort)
	{
		Random r = new Random();
		int n = 53;
		int m = 60;
		int[] array = new int[n];
		System.out.print("Original array: ");
		for (int i = 0; i < n; ++i)
		{
			int x = r.nextInt(m);
			array[i] = x;
			System.out.print(x);
			System.out.print(" ");
		}
		System.out.println();
		sort.execute(array);
		System.out.print("Sorted array:   ");
		for (int i = 0; i < n; ++i)
		{
			System.out.print(array[i]);
			System.out.print(" ");
		}
		System.out.println();
	}
}