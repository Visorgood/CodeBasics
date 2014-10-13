#include <iostream>
#include <cstdlib>
#include <ctime>

#include "ReverseCString.h"
#include "Stack.h"
#include "Queue.h"
#include "Heap.h"

using namespace std;

#define LEN 20
#define MAX_RAND 30

int rand(int max)
{
	return rand() % max;
}

void useReverseCString()
{
	cout << "Usage of the ReverseCString function." << endl;
	string s = "Hello World!";
	cout << "String: " << s << endl;
	char s1[] = "Hello World!";
	ReverseCString(s1);
	cout << "Reversed string: " << s1 << endl << endl;
}

void useStack()
{
	cout << "Usage of the Stack class." << endl;
	Stack<int> stack;
	cout << "Order of pushing: ";
	for (int i = 0; i < LEN; ++i)
	{
		int x = rand(MAX_RAND);
		cout << x << " ";
		stack.push(x);
	}
	cout << endl << "Order of popping: ";
	while (!stack.empty())
		cout << stack.pop() << " ";
	cout << endl << endl;
}

void useQueue()
{
	cout << "Usage of the Queue class." << endl;
	Queue<int> queue;
	cout << "Order of enqueuing: ";
	for (int i = 0; i < LEN; ++i)
	{
		int x = rand(MAX_RAND);
		cout << x << " ";
		queue.enqueue(x);
	}
	cout << endl << "Order of dequeuing: ";
	while (!queue.empty())
		cout << queue.dequeue() << " ";
	cout << endl << endl;
}

void useHeap()
{
	cout << "Usage of the Heap class." << endl;
	Heap<int> heap;
	cout << "Order of insertion:  ";
	for (int i = 0; i < LEN; ++i)
	{
		int x = rand(MAX_RAND);
		cout << x << " ";
		heap.insert(x);
	}
	cout << endl << "Order of extraction: ";
	while (!heap.empty())
		cout << heap.extractMin() << " ";
	cout << endl << endl;
}

int main()
{
	srand(time(NULL));
	useReverseCString();
	useStack();
	useQueue();
	useHeap();
	return 0;
}
