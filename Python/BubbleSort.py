def BubbleSort(A):
	p = True
	while p:
		p = False
		for i in range(1, len(A)):
			if A[i - 1] > A[i]:
				t = A[i - 1]
				A[i - 1] = A[i]
				A[i] = t
				p = True