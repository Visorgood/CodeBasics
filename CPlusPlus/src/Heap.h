#ifndef HEAP_H_
#define HEAP_H_

#include <vector>

using namespace std;

template <class T>
class Heap
{
public:
	Heap() : data(0) {}
	void insert(const T&);
	T extractMin();
	bool empty() { return !data.size(); }
	size_t size() { return data.size(); }
private:
	Heap(const Heap&);
	Heap& operator=(const Heap&);
	void swap(int, int);
	void bubbleUp(int);
	void bubbleDown(int);
	vector<T> data;
};

template <class T>
void Heap<T>::insert(const T& val)
{
	data.push_back(val);
	bubbleUp(data.size() - 1);
}

template <class T>
T Heap<T>::extractMin()
{
	int lastIndex = data.size() - 1;
	swap(0, lastIndex);
	T val = data[lastIndex];
	data.pop_back();
	bubbleDown(0);
	return val;
}

template <class T>
void Heap<T>::swap(int i, int j)
{
	T temp = data[i];
	data[i] = data[j];
	data[j] = temp;
}

template <class T>
void Heap<T>::bubbleUp(int i)
{
	int j = (i - 1) / 2;
	while (j >= 0 && data[i] < data[j])
	{
		swap(i, j);
		i = j;
		j = (i - 1) / 2;
	}
}

template <class T>
void Heap<T>::bubbleDown(int i)
{
	int n = data.size();
	int j1 = i * 2 + 1;
	int j2 = j1 + 1;
	while (j1 < n)
	{
		int j = (j2 >= n || (data[j1] < data[j2]) ? j1 : j2);
		if (data[i] <= data[j])
			break;
		swap(i, j);
		i = j;
		j1 = i * 2 + 1;
		j2 = j1 + 1;
	}
}

#endif /* HEAP_H_ */
