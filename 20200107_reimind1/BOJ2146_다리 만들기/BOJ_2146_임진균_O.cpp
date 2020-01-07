/*
	2019-12-29

	BFS

	Time	:	36 ms
	Memory	:	1500 kb
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
	int begin;

	Pair(int x, int y, int b) : x(x), y(y), begin(b) {}
};

const int dx[4] = { -1, 0, 1, 0 };
const int dy[4] = { 0, 1, 0, -1 };
int N, map[100][100], discovered[100][100];

int solve()
{
	// 섬 마다 번호를 할당한다.
	memset(discovered, 0, sizeof(discovered));
	int count = 1;
	for (int sx = 0; sx < N; sx++)
	{
		for (int sy = 0; sy < N; sy++)
		{
			if (map[sx][sy] && !discovered[sx][sy])
			{
				queue<pair<int, int>> q;
				q.push(make_pair(sx, sy));
				discovered[sx][sy] = 1;

				while (!q.empty())
				{
					int x = q.front().first;
					int y = q.front().second;
					q.pop();

					map[x][y] = count;

					for (int d = 0; d < 4; d++)
					{
						int nx = x + dx[d];
						int ny = y + dy[d];

						if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
						if (map[nx][ny] == 0 || discovered[nx][ny]) continue;

						q.push(make_pair(nx, ny));
						discovered[nx][ny] = 1;
					}
				}
				count++;
			}
		}
	}

	// 각 섬의 경계를 찾는다.
	vector<Pair> start;
	for (int x = 0; x < N; x++)
	{
		for (int y = 0; y < N; y++)
		{
			if (map[x][y] == 0) continue;

			bool boundary = false;
			for (int d = 0; d < 4; d++)
			{
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (map[nx][ny] == 0)
				{
					boundary = true;
					break;
				}
			}

			if (boundary) start.push_back(Pair(x, y, map[x][y]));
		}
	}

	// 각 섬의 경계에서 가장 짧은 다리를 찾는다.
	int ret = 987654321;
	for (int i = 1; i < count; i++)
	{
		queue<Pair> q;
		memset(discovered, -1, sizeof(discovered));

		for (int j = 0; j < start.size(); j++)
		{
			if (start[j].begin != i) continue;
			q.push(start[j]);
			discovered[start[j].x][start[j].y] = 0;
		}

		while (!q.empty())
		{
			int x = q.front().x;
			int y = q.front().y;
			int begin = q.front().begin;
			q.pop();

			if (map[x][y] != 0 && map[x][y] != begin)
			{
				ret = min(ret, discovered[x][y] - 1);
				break;
			}
	
			for (int d = 0; d < 4; d++)
			{
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (map[nx][ny] == begin || discovered[nx][ny] >= 0) continue;

				q.push(Pair(nx, ny, begin));
				discovered[nx][ny] = discovered[x][y] + 1;
			}
		}
	}
	
	return ret;
}

int main(void)
{
	scanf("%d", &N);

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			scanf("%d", &map[i][j]);

	printf("%d\n", solve());

	return 0;
}