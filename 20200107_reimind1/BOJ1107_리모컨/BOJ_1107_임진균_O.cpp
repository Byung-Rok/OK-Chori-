/*
	2019-12-30

	무식하게 풀기

	Time	:	20 ms
	Memory	:	1112 kb
*/

#include <cstdio>
#include <algorithm>

using namespace std;

const int INF = 987654321;
int N, M, buttons[10];

int pushButtons(int channel, int size)
{
	if (channel > 500000) return abs(channel - N) + size;
	if (channel == 0) return N + size;
	
	int ret = abs(channel - N) + size;
	for (int next = 0; next < 10; next++)
	{
		if (!buttons[next]) continue;
		ret = min(ret, pushButtons(channel * 10 + next, size + 1));
	}

	return ret;
}

int solve()
{
	// 숫자 버튼 -> +/- 버튼
	int ret = INF;
	for (int i = 0; i < 10; i++)
	{
		if (!buttons[i]) continue;
		ret = min(ret, pushButtons(i, 1));
	}

	// +/- 버튼만
	return min(ret, abs(N - 100));
}

int main(void)
{
	scanf("%d %d", &N, &M);

	for (int i = 0; i < 10; i++)
		buttons[i] = 1;

	for (int i = 0; i < M; i++)
	{
		int t;
		scanf("%d", &t);
		buttons[t] = 0;
	}

	printf("%d\n", solve());

	return 0;
}