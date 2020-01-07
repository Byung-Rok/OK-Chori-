/*
	2019-12-25
	
	List
	
	Time	:	232 ms
	Memory	:	17608 kb
*/

#include <cstdio>
#include <cstring>
#include <list>

using namespace std;

int main(void)
{
	int T;
	scanf("%d", &T);

	for (int tc = 0; tc < T; tc++)
	{
		char keylog[1000000];
		scanf("%s", keylog);

		int len = strlen(keylog);
		list<char> pw;
		auto cursor = pw.begin();

		for (int i = 0; i < len; i++)
		{
			char &key = keylog[i];

			if (key == '<')
			{
				if (cursor != pw.begin()) 
					cursor--;
			}
			else if (key == '>')
			{
				if (cursor != pw.end()) 
					cursor++;
			}
			else if (key == '-')
			{
				if (cursor != pw.begin())
				{
					cursor--;
					cursor = pw.erase(cursor);
				}
			}
			else
			{
				cursor = pw.insert(cursor, key);
				cursor++;
			}
		}

		for (auto it = pw.begin(); it != pw.end(); it++)
			printf("%c", *it);
		printf("\n");
	}

	return 0;
}