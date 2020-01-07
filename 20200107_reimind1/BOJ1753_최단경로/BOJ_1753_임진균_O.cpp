/*
	2019-12-29

	Dijkstra

	Time	:	152 ms
	Memory	:	8224 kb
*/

#include <cstdio>
#include <queue>
#include <algorithm>

using namespace std;

const int INF = 987654321;
int V, E, K;
vector<pair<int ,int>> adjList[20001];
vector<int> dist(20001, INF);

void solve()
{
	priority_queue<pair<int, int>> pq;
	dist[K] = 0;
	pq.push(make_pair(0, K));

	while (!pq.empty())
	{
		int cost = -pq.top().first;
		int here = pq.top().second;
		pq.pop();

		for (int i = 0; i < adjList[here].size(); i++)
		{
			int &there = adjList[here][i].first;
			int &w = adjList[here][i].second;

			if (cost + w >= dist[there]) continue;
			dist[there] = cost + w;
			pq.push(make_pair(-dist[there], there));
		}
	}
}

int main(void)
{
	scanf("%d %d %d", &V, &E, &K);

	for (int i = 0; i < E; i++)
	{
		int u, v, w;
		scanf("%d %d %d", &u, &v, &w);
		adjList[u].push_back(make_pair(v, w));
	}

	solve();

	for (int v = 1; v <= V; v++)
	{
		if(dist[v] != INF) printf("%d\n", dist[v]);
		else printf("%s\n", "INF");
	}

	return 0;
}