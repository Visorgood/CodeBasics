# array - array of values
# x - value to find
# return value is an index of matched element
# if x is not in A, then -1 is returned


def binarysearch(array, x):
    return binarysearchrec(array, 0, len(array) - 1, x)


def binarysearchrec(array, start, end, x):
    if start > end:
        return -1
    i = (start + end) / 2
    if array[i] == x:
        return i
    elif array[i] > x:
        return binarysearchrec(array, start, i - 1, x)
    else:
        return binarysearchrec(array, i + 1, end, x)

import random
array = [0]
length = 50
for i in range(1, length):
    array.append(array[i - 1] + random.randint(1, 3))
print "Array:", array

x = random.randrange(array[length - 1] + 1)
print "x =", x
print "index of x is", binarysearch(array, x)