import java.util.Random;

public class QuickSort implements Sort
{
	private Random rand = new Random();
	
	public void execute(int[] array)
	{
		executeRec(array, 0, array.length - 1);
	}
	
	private void executeRec(int[] array, int l, int r)
	{
		if (l >= r) return;
		int p = partition(array, l, r);
		executeRec(array, l, p - 1);
		executeRec(array, p + 1, r);
	}
	
	private int partition(int[] array, int l, int r)
	{
		swap(array, l, rand.nextInt(r - l + 1) + l);
		int p = array[l];
		int j = l + 1;
		for (int i = l + 1; i <= r; ++i)
			if (array[i] < p)
				swap(array, i, j++);
		swap(array, l, j - 1);
		return j - 1;
	}
	
	private void swap(int[] array, int i, int j)
	{
		int t = array[i];
		array[i] = array[j];
		array[j] = t;
	}
}
