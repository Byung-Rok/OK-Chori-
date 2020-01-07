/*
	2020-01-03

	DP, Memoization, Bitmasking

	Time	:	28 ms
	Memory	:	20312 kb
*/

#include <cstdio>
#include <cstring>
#include <algorithm>

using namespace std;

int N, W[15][15], cache[15][10][1 << 15];

int solve(int owner, int price, int all)
{
	int &ret = cache[owner][price][all];
	if (ret != -1) return ret;

	ret = 1;
	for (int next = 0; next < N; next++)
	{
		if (W[owner][next] < price) continue;
		if (all & (1 << next)) continue;
		ret = max(ret, solve(next, W[owner][next], all | (1 << next)) + 1);
	}

	return ret;
}

int main(void)
{
	scanf("%d", &N);
	for (int i = 0; i < N; i++)
	{
		char temp[16];
		scanf("%s", temp);

		for (int j = 0; j < N; j++)
			W[i][j] = temp[j] - '0';
	}

	memset(cache, -1, sizeof(cache));
	printf("%d\n", solve(0, 0, 1 << 0));

	return 0;
}