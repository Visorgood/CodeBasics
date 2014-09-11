def FindMax(A):
	if len(A) == 0:
		return 0, -1
	max = A[0]
	maxIndex = 0
	for i in range(1, len(A)):
		if A[i] > max:
			max = A[i]
			maxIndex = i
	return max, maxIndex