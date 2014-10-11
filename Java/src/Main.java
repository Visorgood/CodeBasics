public class Main
{
	public static void main(String[] args)
	{
		java.util.Random r = new java.util.Random();
		int n = 101;
		int[] array = new int[n];
		for (int i = 0; i < n; ++i)
			array[i] = r.nextInt(n);
		System.out.println("Original array:");
		for (int i = 0; i < n; ++i)
		{
			System.out.print(array[i]);
			System.out.print(" ");
		}
		System.out.println();
		MergeSort.Merge(array);
		System.out.println("Sorted array:");
		for (int i = 0; i < n; ++i)
		{
			System.out.print(array[i]);
			System.out.print(" ");
		}
	}
}