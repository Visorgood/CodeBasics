#include <iostream>
#include "ReverseCString.h"
#include "Stack.h"
#include "Queue.h"
using namespace std;

void useReverseCString()
{
	string s = "Hello World!";
	cout << s << endl;
	char s1[] = "Hello World!";
	ReverseCString(s1);
	cout << s1 << endl;
}

void useStack()
{
	Stack<int> stack;
	stack.push(5);
	stack.push(2);
	stack.push(7);
	cout << stack.pop() << endl;
	cout << stack.pop() << endl;
	cout << stack.pop() << endl;
	cout << stack.pop() << endl;
}

void useQueue()
{
	Queue<int> queue;
	queue.enqueue(5);
	queue.enqueue(2);
	queue.enqueue(7);
	cout << queue.dequeue() << endl;
	cout << queue.dequeue() << endl;
	cout << queue.dequeue() << endl;
	cout << queue.dequeue() << endl;
}

int main()
{
	//useReverseCString();
	//useStack();
	//useQueue();
	return 0;
}
