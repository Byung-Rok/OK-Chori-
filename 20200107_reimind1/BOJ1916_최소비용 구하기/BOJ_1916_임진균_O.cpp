/*
	2019-12-29

	Dijkstra
	
	** 주의
		문제에서 A->B로 가는 비용이 여러 개 주어질 수 있음. 
		인접리스트는 상관없지만 인접행렬를 사용한다면 심각한 문제.

	Time	:	40 ms
	Memory	:	5136 kb
*/

#include <cstdio>
#include <queue>
#include <algorithm>

using namespace std;

const int INF = 987654321;
int N, M, A, B;
vector<vector<int>> W(1001, vector<int>(1001, INF));
vector<int> dist(1001, INF);

int solve()
{
	priority_queue<pair<int, int>> pq;
	dist[A] = 0;
	pq.push(make_pair(0, A));

	while (!pq.empty())
	{
		int cost = -pq.top().first;
		int here = pq.top().second;
		pq.pop();

		for (int there = 1; there <= N; there++)
		{
			if (cost + W[here][there] >= dist[there]) continue;
			dist[there] = cost + W[here][there];
			pq.push(make_pair(-dist[there], there));
		}
	}

	return dist[B];
}

int main(void)
{
	scanf("%d %d", &N, &M);

	for (int i = 0; i < M; i++)
	{
		int a, b, c;
		scanf("%d %d %d", &a, &b, &c);
		W[a][b] = min(W[a][b], c);
	}
	scanf("%d %d", &A, &B);

	printf("%d\n", solve());

	return 0;
}