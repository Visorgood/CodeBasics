import random

def Swap(A, i, j):
	t = A[i]
	A[i] = A[j]
	A[j] = t

def Partition(A, l, r):
	Swap(A, l, random.randint(l, r))
	p = A[l]
	i = l + 1
	for j in range(l + 1, r + 1):
		if A[j] < p:
			Swap(A, i, j)
			i = i + 1
	Swap(A, l, i - 1)
	return i - 1

def RSelect(A, l, r, i):
	if l >= r:
		return A[r]
	j = Partition(A, l, r)
	print A[j], j, A
	if j == i:
		return A[j]
	if j > i:
		return RSelect(A, l, j - 1, i)
	if j < i:
		return RSelect(A, j + 1, r, i - j)

def FindOrdinalStatistic(A, i):
	return RSelect(A, 0, len(A) - 1, i)

array = [random.randint(1, 30) for i in range(10)]
print array
print sorted(array)
print
statisticNumber = 5
ordinalStatistic = FindOrdinalStatistic(array, statisticNumber)
print
print statisticNumber, ordinalStatistic