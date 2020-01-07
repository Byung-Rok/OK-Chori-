/*
	2020-01-02

	DP, Memoization

	Time	:	0 ms
	Memory	:	1116 kb
*/

#include <cstdio>
#include <cstring>

const int div = 1000000000;
int N, cache[10][101];

int solve(int cur, int len)
{
	if (len == N) return 1;
	int &ret = cache[cur][len];
	if (ret != -1) return ret;

	ret = 0;
	if (cur > 0) ret = (ret + solve(cur - 1, len + 1)) % div;
	if (cur < 9) ret = (ret + solve(cur + 1, len + 1)) % div;
	return ret;
}

int main(void)
{
	scanf("%d", &N);

	memset(cache, -1, sizeof(cache));

	int ret = 0;
	for (int start = 1; start <= 9; start++)
		ret = (ret + solve(start, 1)) % div;

	printf("%d\n", ret);

	return 0;
}