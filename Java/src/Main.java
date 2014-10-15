import java.util.Random;

public class Main
{
	public static void main(String[] args)
	{
		useMergeSort();
	}
	
	static void useMergeSort()
	{
		Random r = new Random();
		int n = 29;
		int m = 30;
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
		MergeSort.Execute(array);
		System.out.print("Sorted array:   ");
		for (int i = 0; i < n; ++i)
		{
			System.out.print(array[i]);
			System.out.print(" ");
		}
		System.out.println();
	}
}