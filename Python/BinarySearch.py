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