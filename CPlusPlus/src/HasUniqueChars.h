#ifndef HASUNIQUECHARS_H_
#define HASUNIQUECHARS_H_

#include <string>

using namespace std;

bool HasUniqueChars(const string& str)
{
	char dc[256] = { 0 };
	for (string::const_iterator it = str.begin(); it != str.end(); ++it)
	{
		if (dc[*it] > 0)
			return false;
		dc[*it] += 1;
	}
	return true;
}

#endif /* HASUNIQUECHARS_H_ */
