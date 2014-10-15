public class MergeSort
{
	public static void Execute(int[] array)
	{
		ExecuteRec(array, 0, array.length - 1);
	}

	private static void ExecuteRec(int[] array, int s, int e)
	{
		if (e <= s)	return;
		int m = (e + s) / 2;
		ExecuteRec(array, s, m);
		ExecuteRec(array, m + 1, e);
		MergeSubarrays(array, s, e, m);
	}
	
	private static void MergeSubarrays(int[] array, int s, int e, int m)
	{
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
