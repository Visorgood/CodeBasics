public class MergeSort implements Sort
{
	public void execute(int[] array)
	{
		executeRec(array, 0, array.length - 1);
	}

	private void executeRec(int[] array, int s, int e)
	{
		if (e <= s)	return;
		int m = (e + s) / 2;
		executeRec(array, s, m);
		executeRec(array, m + 1, e);
		mergeSubarrays(array, s, e, m);
	}
	
	private void mergeSubarrays(int[] array, int s, int e, int m)
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
