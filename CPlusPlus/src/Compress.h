#ifndef COMPRESS_H_
#define COMPRESS_H_

#include <string.h>

unsigned int Compress(char* str)
{
	size_t len = strlen(str);
	if (len <= 2)
		return 0;
	char* com = new char[len - 1];
	unsigned int comIndex = 0;
	char c = str[0];
	unsigned int count = 1;
	for (unsigned int i = 1; i < len; ++i)
	{
		if (str[i] == c)
			++count;
		else
		{
			com[comIndex++] = c;
			com[comIndex++] = '0' + count;
			c = str[i];
			count = 1;
			if (comIndex + 2 >= len)
				return 0;
		}
	}
	com[comIndex++] = c;
	com[comIndex++] = '0' + count;
	for (unsigned int i = 0; i < comIndex; ++i)
		str[i] = com[i];
	str[comIndex] = 0;
	delete[] com;
	return comIndex;
}

#endif /* COMPRESS_H_ */
