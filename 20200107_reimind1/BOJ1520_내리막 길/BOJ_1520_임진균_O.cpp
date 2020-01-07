/*
	2019-12-30

	DP, Memoization

	Time	:	36 ms
	Memory	:	3064 kb
*/

#include <cstdio>
#include <cstring>

const int dx[4] = { -1, 0, 1, 0 };
const int dy[4] = { 0, 1, 0, -1 };
int M, N, map[500][500], cache[500][500];

int solve(int x, int y)
{
	if (x == M - 1 && y == N - 1) return 1;
	
	int &ret = cache[x][y];
	if (ret != -1) return ret;

	ret = 0;
	for (int d = 0; d < 4; d++)
	{
		int nx = x + dx[d];
		int ny = y + dy[d];

		if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
		if (map[x][y] <= map[nx][ny]) continue;
		
		ret += solve(nx, ny);
	}

	return ret;
}

int main(void)
{
	scanf("%d %d", &M, &N);

	for (int i = 0; i < M; i++)
		for (int j = 0; j < N; j++)
			scanf("%d", &map[i][j]);

	memset(cache, -1, sizeof(cache));
	printf("%d\n", solve(0, 0));

	return 0;
}