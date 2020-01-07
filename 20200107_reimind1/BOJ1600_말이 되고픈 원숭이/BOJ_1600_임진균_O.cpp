/*
	2019-12-30

	BFS

	** 주의
		H, W 입력 순서 주의할 것.
		(H, x), (W, y) 쌍으로 작성되어 있는지 항상 확인할 것.

	Time	:	104 ms
	Memory	:	6224 kb
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
const int dx[12] = { -1, 0, 1, 0, -2, -1, 1, 2, 2, 1, -1, -2 };
const int dy[12] = { 0, 1, 0, -1, 1, 2, 2, 1, -1, -2, -2, -1 };

int K, W, H, map[200][200], discovered[200][200][31];

int solve()
{
	queue<Pair> q;

	for (int i = 0; i < H; i++)
		for (int j = 0; j < W; j++)
			for (int k = 0; k <= K; k++)
				discovered[i][j][k] = INF;
	
	q.push(Pair(0, 0, K));
	discovered[0][0][K] = 0;

	while (!q.empty())
	{
		int x = q.front().x;
		int y = q.front().y;
		int k = q.front().k;
		q.pop();

		int dEnd = (k > 0 ? 12 : 4);
		for (int d = 0; d < dEnd; d++)
		{
			int nx = x + dx[d];
			int ny = y + dy[d];
			int nk = (d < 4 ? k : k - 1);

			if (nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
			if (map[nx][ny]) continue;
			if (discovered[x][y][k] + 1 >= discovered[nx][ny][nk]) continue;

			q.push(Pair(nx, ny, nk));
			discovered[nx][ny][nk] = discovered[x][y][k] + 1;
		}
	}

	int ret = INF;
	for (int k = 0; k <= K; k++)
		ret = min(ret, discovered[H - 1][W - 1][k]);

	return ret != INF ? ret : -1;
}

int main(void)
{
	scanf("%d %d %d", &K, &W, &H);

	for (int i = 0; i < H; i++)
		for (int j = 0; j < W; j++)
			scanf("%d", &map[i][j]);

	printf("%d\n", solve());

	return 0;
}