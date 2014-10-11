# A - array of values
# x - value to find
# return value is an index of matched element
# if x is not in A, then -1 is returned

def BinarySearch(A, x):
      return BinarySearchRec(A, 0, len(A) - 1, x)

def BinarySearchRec(A, start, end, x):
      if start > end:
            return -1
      i = (start + end) / 2
      if A[i] == x:
            return i
      elif A[i] > x:
            return BinarySearchRec(A, start, i - 1, x)
      else:
            return BinarySearchRec(A, i + 1, end, x)

import random
A = [0]
N = 50
for i in range(1, N):
    A.append(A[i - 1] + random.randint(1, 3))
print "Array:", A

x = random.randrange(A[N - 1] + 1)
print "x =", x
print "index of x is", BinarySearch(A, x)