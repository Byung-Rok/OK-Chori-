/*
	2019-12-29

	MST

	Time	:	44 ms
	Memory	:	3496 kb
*/

#include <cstdio>
#include <queue>
#include <algorithm>

using namespace std;

int N, M, parent[1001];
priority_queue<pair<int, pair<int, int>>> pq;

void makeSet()
{
	for (int v = 1; v <= 1000; v++)
		parent[v] = v;
}

int findSet(int v)
{
	if (parent[v] == v) return v;
	return parent[v] = findSet(parent[v]);
}

void unionSet(int u, int v)
{
	u = findSet(u);
	v = findSet(v);

	if (u != v) parent[u] = v;
}

int main(void)
{
	scanf("%d %d", &N, &M);

	for (int i = 0; i < M; i++)
	{
		int a, b, c;
		scanf("%d %d %d", &a, &b, &c);
		pq.push(make_pair(-c, make_pair(a, b)));
	}

	makeSet();

	int ret = 0, count = 0;
	for (int i = 0; i < M; i++)
	{
		if (count == N - 1) break;

		int cost = -pq.top().first;
		int u = pq.top().second.first;
		int v = pq.top().second.second;
		pq.pop();

		if (findSet(u) != findSet(v))
		{
			unionSet(u, v);
			ret += cost;
			count++;
		}
	}

	printf("%d\n", ret);

	return 0;
}