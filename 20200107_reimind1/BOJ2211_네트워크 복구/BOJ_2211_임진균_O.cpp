/*
	2019-12-26

	Dijkstra

	Time	:	72 ms
	Memory	:	5088 kb
*/

#include <cstdio>
#include <queue>
#include <algorithm>

using namespace std;

const int INF = 987654321;

int N, M, w[1000][1000] = { 0 };
int dist[1000], conn[1000];

void solve()
{
	priority_queue<pair<int, int>> pq;
	dist[0] = 0;
	pq.push(make_pair(0, 0));

	while (!pq.empty())
	{
		int cost = -pq.top().first;
		int here = pq.top().second;
		pq.pop();

		for (int there = 0; there < N; there++)
		{
			if (cost + w[here][there] < dist[there])
			{
				dist[there] = cost + w[here][there];
				pq.push(make_pair(-dist[there], there));
				conn[there] = here;
			}
		}
	}
}

int main(void)
{
	scanf("%d %d", &N, &M);

	for (int u = 0; u < N; u++)
		for (int v = 0; v < N; v++)
			w[u][v] = INF;

	for (int v = 0; v < N; v++)
		dist[v] = conn[v] = INF;

	for (int i = 0; i < M; i++)
	{
		int A, B, C;
		scanf("%d %d %d", &A, &B, &C);
		w[A - 1][B - 1] = w[B - 1][A - 1] = C;
	}
	
	solve();

	int K = 0;
	for (int v = 0; v < N; v++)
		if (conn[v] != INF)
			K++;
	printf("%d\n", K);

	for (int v = 0; v < N; v++)
		if (conn[v] != INF)
			printf("%d %d\n", conn[v] + 1, v + 1);

	return 0;
}