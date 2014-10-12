#include "ReverseCString.h"

void ReverseCString(char* str)
{
	if (!str)
		return;
	char* end = str;
	while (*end)
		++end;
	--end;
	while (str < end)
	{
		char c = *str;
		*str++ = *end;
		*end-- = c;
	}
}
