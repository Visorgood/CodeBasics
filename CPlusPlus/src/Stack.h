#ifndef STACK_H_
#define STACK_H_

template <class T>
class Stack
{
public:
	Stack() : head(0) {}
	~Stack();
	void push(const T&);
	T pop();
	bool empty() { return !head; }
private:
	Stack(const Stack&);
	Stack& operator=(const Stack&);
	struct Node
	{
		T value;
		Node* next;
		Node(const T& val) : value(val), next(0) {}
	};
	Node* head;
};

template <class T>
Stack<T>::~Stack()
{
    while (!empty())
        pop();
}

template <class T>
void Stack<T>::push(const T& value)
{
	Node* node = new Node(value);
	node->next = head;
	head = node;
}

template <class T>
T Stack<T>::pop()
{
	if (empty())
		throw "Stack is empty!";
	T value = head->value;
	Node* node = head;
	head = head->next;
	delete node;
	return value;
}

#endif /* STACK_H_ */
