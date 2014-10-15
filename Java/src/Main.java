import java.util.Random;

public class Main
{
	public static void main(String[] args)
	{
		useSort(new MergeSort());
		useSort(new QuickSort());
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