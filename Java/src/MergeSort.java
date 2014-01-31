
public class MergeSort
{
	public static void Merge(int[] array)
	{
		Merge(array, 0, array.length - 1);
	}

	private static void Merge(int[] array, int s, int e)
	{
		if (e - s <= 1)
			return;
		
		int middle = (e + s) / 2;
		Merge(array, s, middle);
		Merge(array, middle + 1, e);
		
		int[] merged = new int[e - s + 1];
		int i = s, j = middle + 1;
		for (int k = 0; k < merged.length; ++k)
		{
			if (i > middle)
				merged[k] = array[j++];
			else if (j > e)
				merged[k] = array[i++];
			else
			{
				if (array[i] < array[j])
					merged[k] = array[i++];
				else
					merged[k] = array[j++];
			}	
		}
	}
}
