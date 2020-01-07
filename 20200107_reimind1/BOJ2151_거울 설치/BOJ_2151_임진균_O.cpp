/*
	2020-01-02

	BFS

	Time	:	8 ms
	Memory	:	1228 kb
*/

#include <cstdio>
#include <cstring>
#include <queue>
#include <algorithm>

using namespace std;

struct Pair
{
	int x;
	int y;
	int d;

	Pair(int x, int y, int d) : x(x), y(y), d(d) {}
};

const int dx[4] = { -1, 0, 1, 0 };
const int dy[4] = { 0, 1, 0, -1 };
const int reflect[3][4] =
{
	{ 0, 1, 2, 3 }, // 반사 없음
	{ 3 ,2 ,1 ,0 }, // '\' 거울
	{ 1, 0, 3, 2 }  // '/' 거울
};

int N;
char map[50][51];
vector<pair<int, int>> doors;

int solve()
{
	int ret = 987654321, discovered[50][50][4];
	memset(discovered, -1, sizeof(discovered));

	queue<Pair> q;
	for (int d = 0; d < 4; d++)
	{
		int &x = doors[0].first;
		int &y = doors[0].second;
		q.push(Pair(x, y, d));
		discovered[x][y][d] = 0;
	}

	while (!q.empty())
	{
		int x = q.front().x;
		int y = q.front().y;
		int d = q.front().d; q.pop();

		if (x == doors[1].first && y == doors[1].second)
			ret = min(ret, discovered[x][y][d]);

		int iend = (map[x][y] != '!' ? 1 : 3);
		for (int i = 0; i < iend; i++)
		{
			int nd = reflect[i][d];
			int nx = x + dx[nd];
			int ny = y + dy[nd];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			if (map[nx][ny] == '*') continue;

			int ncount = discovered[x][y][d] + (i == 0 ? 0 : 1);
			if (discovered[nx][ny][nd] != -1 && discovered[nx][ny][nd] < ncount) continue;
			
			q.push(Pair(nx, ny, nd));
			discovered[nx][ny][nd] = ncount;
		}
	}

	return ret;
}

int main(void)
{
	scanf("%d", &N);
	for (int i = 0; i < N; i++)
	{
		scanf("%s", map[i]);

		for (int j = 0; j < N; j++)
			if (map[i][j] == '#') doors.push_back(make_pair(i, j));
	}

	printf("%d\n", solve());

	return 0;
}