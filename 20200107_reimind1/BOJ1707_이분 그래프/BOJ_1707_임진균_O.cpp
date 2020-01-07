/*
	2020-01-03

	BFS

	** 주의
		한 그래프의 모든 정점들이 서로 연결되어 있다는 보장은 없다.

	Time	:	376 ms
	Memory	:	10420 kb
*/

#include <cstdio>
#include <cstring>
#include <queue>
#include <algorithm>

using namespace std;

int T, V, E, u, v;
vector<int> adjList[20001];
char discovered[20001];

bool solve()
{
	memset(discovered, 0, sizeof(discovered));

	for (int v = 1; v <= V; v++)
	{
		if (discovered[v]) continue;

		queue<int> q;
		q.push(v);
		discovered[v] = 'R';

		while (!q.empty())
		{
			int here = q.front(); q.pop();
			for (int i = 0; i < adjList[here].size(); i++)
			{
				int there = adjList[here][i];
				if (discovered[there])
				{
					if (discovered[here] == discovered[there]) return false;
					else continue;
				}

				q.push(there);
				discovered[there] = (discovered[here] == 'R' ? 'B' : 'R');
			}
		}
	}

	return true;
}

int main(void)
{
	scanf("%d", &T);
	for (int tc = 0; tc < T; tc++)
	{
		for (int i = 1; i <= V; i++)
			adjList[i].clear();

		scanf("%d %d", &V, &E);
		for (int i = 0; i < E; i++)
		{
			scanf("%d %d", &u, &v);
			adjList[u].push_back(v);
			adjList[v].push_back(u);
		}

		printf("%s\n", solve() ? "YES" : "NO");
	}

	return 0;
}