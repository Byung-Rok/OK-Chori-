/*
	2019-12-30

	BFS, Bitmask

	Time	:	0 ms
	Memory	:	1852 kb
*/

#include <cstdio>
#include <queue>
#include <algorithm>

using namespace std;

struct Pair
{
	int x;
	int y;
	int k;

	Pair(int x, int y, int k) : x(x), y(y), k(k) {}
};

const int INF = 987654321;
const int dx[4] = { -1, 0, 1, 0 };
const int dy[4] = { 0, 1, 0, -1 };

int N, M, discovered[50][50][1 << 6];
char map[51][51];

int solve(pair<int, int> &start)
{
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			fill(discovered[i][j], discovered[i][j] + (1 << 6), INF);

	queue<Pair> q;
	q.push(Pair(start.first, start.second, 0));
	discovered[start.first][start.second][0] = 0;

	while (!q.empty())
	{
		int x = q.front().x;
		int y = q.front().y;
		int k = q.front().k;
		q.pop();

		if (map[x][y] == '1') return discovered[x][y][k];

		for (int d = 0; d < 4; d++)
		{
			int nx = x + dx[d];
			int ny = y + dy[d];
			int nk = k;

			if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

			if (map[nx][ny] == '#') continue;
			if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f')
				nk |= (1 << (map[nx][ny] - 'a'));
			if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F')
				if (!(k & (1 << (map[nx][ny] - 'A')))) continue;

			if (discovered[x][y][k] + 1 >= discovered[nx][ny][nk]) continue;
			
			q.push(Pair(nx, ny, nk));
			discovered[nx][ny][nk] = discovered[x][y][k] + 1;
		}
	}

	return -1;
}

int main(void)
{
	scanf("%d %d", &N, &M);

	pair<int, int> start;
	for (int i = 0; i < N; i++)
	{
		scanf("%s", map[i]);

		for (int j = 0; j < M; j++)
			if (map[i][j] == '0') start = pair<int, int>(i, j);
	}

	printf("%d\n", solve(start));

	return 0;
}