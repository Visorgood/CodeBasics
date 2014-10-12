#ifndef QUEUE_H_
#define QUEUE_H_

template <class T>
class Queue
{
public:
	Queue() : head(0), tail(0) {}
	~Queue();
	void enqueue(const T&);
	T dequeue();
	bool empty() { return !head; }
private:
	Queue(const Queue&);
	Queue& operator=(const Queue&);
	struct Node
	{
		T value;
		Node* next;
		Node* prev;
		Node(const T& val) : value(val), next(0), prev(0) {}
	};
	Node* head;
	Node* tail;
};

template <class T>
Queue<T>::~Queue()
{
    while (!empty())
        dequeue();
}

template <class T>
void Queue<T>::enqueue(const T& value)
{
	Node* node = new Node(value);
	if (head)
	{
		node->next = head;
		head->prev = node;
	}
	head = node;
	if (!tail)
		tail = head;
}

template <class T>
T Queue<T>::dequeue()
{
	if (empty())
		throw "Queue is empty!";
	T value = tail->value;
	Node* node = tail;
	tail = tail->prev;
	if (!tail)
		head = 0;
	delete node;
	return value;
}

#endif /* QUEUE_H_ */
