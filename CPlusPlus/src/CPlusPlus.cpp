#include <iostream>
#include "ReverseCString.h"
using namespace std;

int main()
{
	string s = "Hello World!";
	cout << s << endl;
	char s1[] = "Hello World!";
	ReverseCString(s1);
	cout << s1 << endl;
	return 0;
}
