def Swap(A, i, j):
	t = A[i]
	A[i] = A[j]
	A[j] = t

def Partition(A, l, r):
	p = A[l]
	i = l + 1
	for j in range(l + 1, r + 1):
		if A[j] < p:
			Swap(A, i, j)
			i = i + 1
	Swap(A, l, i - 1)
	return i - 1

def QuickSortRecursive(A, l, r):
	if l >= r:
		return
	i = Partition(A, l, r)
	QuickSortRecursive(A, l, i - 1)
	QuickSortRecursive(A, i + 1, r)

def QuickSort(array):
	QuickSortRecursive(array, 0, len(array) - 1)