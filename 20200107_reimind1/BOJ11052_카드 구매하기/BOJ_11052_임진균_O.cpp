/*
	2020-01-02

	DP, Memoization

	Time	:	0 ms
	Memory	:	1120 kb
*/

#include <cstdio>
#include <cstring>
#include <algorithm>

using namespace std;

int N, P[1001], cache[1001];

int solve(int cards)
{
	if (cards == 0) return 0;
	int &ret = cache[cards];
	if (ret != -1) return ret;

	ret = 0;
	for (int i = 1; i <= N; i++)
	{
		if (cards - i < 0) continue;
		ret = max(ret, solve(cards - i) + P[i]);
	}

	return ret;
}

int main(void)
{
	scanf("%d", &N);
	for (int i = 1; i <= N; i++)
		scanf("%d", &P[i]);

	memset(cache, -1, sizeof(cache));

	printf("%d\n", solve(N));

	return 0;
}