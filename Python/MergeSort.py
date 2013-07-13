def Merge(B, C):
	D = []
	lenB = len(B)
	lenC = len(C)
	i = 0
	j = 0
	while i < lenB and j < lenC:
		if B[i] < C[j]:
			D.append(B[i])
			i += 1
		else:
			D.append(C[j])
			j += 1
	if i < lenB:
		while i < lenB:
			D.append(B[i])
			i += 1
	else:
		while j < lenC:
			D.append(C[j])
			j += 1
	return D

def MergeSort(A):
	if len(A) == 1:
		return A
	else:
		half = len(A) / 2
		B = MergeSort(A[:half])
		C = MergeSort(A[half:])
		D = Merge(B, C)
		return D