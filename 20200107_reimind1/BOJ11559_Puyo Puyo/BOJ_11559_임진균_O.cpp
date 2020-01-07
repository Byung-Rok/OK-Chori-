/*
	2020-01-02

	BFS

	Time	:	0 ms
	Memory	:	1224 kb
*/

#include <cstdio>
#include <queue>
#include <algorithm>

using namespace std;

const int dx[4] = { -1, 0, 1, 0 };
const int dy[4] = { 0, 1, 0, -1 };
char map[12][7];

int solve()
{
	bool poped = false;
	int combo = 0;

	while (1)
	{
		bool discovered[12][6] = { 0 };
		for (int i = 11; i >= 0; i--)
		{
			for (int j = 0; j < 6; j++)
			{
				if (map[i][j] == '.' || discovered[i][j]) continue;

				vector<pair<int, int>> puyos;
				queue<pair<int, int>> q;
				q.push(make_pair(i, j));
				discovered[i][j] = true;

				while (!q.empty())
				{
					int x = q.front().first;
					int y = q.front().second; q.pop();
					puyos.push_back(make_pair(x, y));

					for (int d = 0; d < 4; d++)
					{
						int nx = x + dx[d];
						int ny = y + dy[d];

						if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6) continue;
						if (map[x][y] != map[nx][ny] || discovered[nx][ny]) continue;
						q.push(make_pair(nx, ny));
						discovered[nx][ny] = true;
					}
				}

				if (puyos.size() >= 4)
				{
					for (int k = 0; k < puyos.size(); k++)
						map[puyos[k].first][puyos[k].second] = '.';
					poped = true;
				}
			}
		}

		if (!poped) break;

		combo++;
		poped = false;
		for (int i = 11; i >= 0; i--)
		{
			for (int j = 0; j < 6; j++)
			{
				if (map[i][j] == '.') continue;

				int len = 0, temp;
				for (int k = i + 1; k < 12 && map[k][j] == '.'; k++, len++);

				temp = map[i][j];
				map[i][j] = '.';
				map[i + len][j] = temp;
			}
		}
	}

	return combo;
}

int main(void)
{
	for(int i = 0 ; i < 12 ; i++)
		scanf("%s", map[i]);

	printf("%d\n", solve());

	return 0;
}