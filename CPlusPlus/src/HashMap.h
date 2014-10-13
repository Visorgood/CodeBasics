#ifndef HASHMAP_H_
#define HASHMAP_H_

#include <vector>

using namespace std;

template <class TKey, class TValue>
class HashMap
{
public:
	HashMap();
	void put(const TKey&, const TValue&);
	TValue get(const TKey&) const;
	void remove(const TKey&);
	bool contains(const TKey&) const;
	bool empty() const { return 0 == keys.size(); }
	size_t size() const { return keys.size(); }
private:
	HashMap(const HashMap&);
	HashMap& operator=(const HashMap&);
	size_t getHash(const TKey&) const;
	struct Node
	{
		TKey* key;
		TValue value;
		Node* next;
		Node(const TKey* k, const TValue& v, Node* n = 0) :  key(k), value(v), next(n) {}
	private:
		Node();
		Node(const Node&);
		Node& operator=(const Node&);
	};
	vector<TKey> keys;
	vector<Node*> values;
	//size_t n;
};

template <class TKey, class TValue>
HashMap<TKey, TValue>::HashMap() : keys(0), values(101, 0)//, n(0)
{}

template <class TKey, class TValue>
void HashMap<TKey, TValue>::put(const TKey& key, const TValue& value)
{
	size_t hash = getHash(key);
	Node* node = values[hash];
	if (!node)
	{
		keys.push_back(key);
		values[hash] = new Node(&keys.back(), value);
		//++n;
	}
	else do
	{
		if (key == *node->key)
		{
			node->value = value;
			return;
		}
		if (!node->next)
		{
			keys.push_back(key);
			node->next = new Node(&keys.back(), value);
			//++n;
			return;
		}
		node = node->next;
	}
	while (true);
}

template <class TKey, class TValue>
TValue HashMap<TKey, TValue>::get(const TKey& key) const
{
	size_t hash = getHash(key);
	for (Node* node = values[hash]; node; node = node->next)
		if (key == *node->key)
			return node->value;
	return 0;
}

template <class TKey, class TValue>
void HashMap<TKey, TValue>::remove(const TKey& key)
{
	size_t hash = getHash(key);
	Node* node = values[hash];
	if (!node)
		return;
	if (key == *node->key)
	{
		values[hash] = node->next;
		delete node;
		values.
		//--n;
		return;
	}
	while (node->next)
	{
		if (key == node->next->key)
		{
			Node* temp = node->next;
			node->next = node->next->next;
			delete temp;
			--n;
			return;
		}
		node = node->next;
	}
}

template <class TKey, class TValue>
bool HashMap<TKey, TValue>::contains(const TKey& key) const
{
	size_t hash = getHash(key);
	for (Node* node = data[hash]; node; node = node->next)
		if (key == node->key)
			return true;
	return false;
}

template <class TKey, class TValue>
size_t HashMap<TKey, TValue>::getHash(const TKey& key) const
{
	size_t res = 0;
	size_t len = sizeof(TKey);
	const char* p = reinterpret_cast<const char*>(&key);
	while (len--)
		res = (res << 1)^*p++;
	return res % data.size();
}

#endif /* HASHMAP_H_ */
