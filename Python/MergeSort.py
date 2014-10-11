def mergesort(array):
    if len(array) == 1:
        return array
    else:
        middle = len(array) / 2
        half1 = mergesort(array[:middle])
        half2 = mergesort(array[middle:])
        mergedarray = merge(half1, half2)
        return mergedarray


def merge(half1, half2):
    mergedarray = []
    lenhalf1 = len(half1)
    lenhalf2 = len(half2)
    i = 0
    j = 0
    while i < lenhalf1 and j < lenhalf2:
        if half1[i] < half2[j]:
            mergedarray.append(half1[i])
            i += 1
        else:
            mergedarray.append(half2[j])
            j += 1
    if i < lenhalf1:
        while i < lenhalf1:
            mergedarray.append(half1[i])
            i += 1
    else:
        while j < lenhalf2:
            mergedarray.append(half2[j])
            j += 1
    return mergedarray

import random
array = []
for i in range(50):
    array.append(random.randrange(100))
print "Array:", array
print "Sorted array:", mergesort(array)