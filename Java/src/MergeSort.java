
public class MergeSort
{
	public static void Merge(int[] array)
	{
		Merge(array, 0, array.length - 1);
	}

	private static void Merge(int[] array, int s, int e)
	{
		if (e - s < 1)
			return;
		
		int m = (e + s) / 2;
		Merge(array, s, m);
		Merge(array, m + 1, e);
		
		int[] merged = new int[e - s + 1];
		int i = s, j = m + 1;
		for (int k = 0; k < merged.length; ++k)
		{
			if (i > m)
				merged[k] = array[j++];
			else if (j > e)
				merged[k] = array[i++];
			else
				merged[k] = (array[i] < array[j] ? array[i++] : array[j++]);
		}
		for (int k = 0; k < merged.length; ++k)
			array[s + k] = merged[k];
	}
}
