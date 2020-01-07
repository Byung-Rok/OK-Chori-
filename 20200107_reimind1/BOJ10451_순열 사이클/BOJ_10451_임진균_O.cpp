/*
	2020-01-02

	DFS

	Time	:	64 ms
	Memory	:	1116 kb
*/

#include <cstdio>
#include <cstring>

int T, N, adjList[1001];
bool visited[1001];

void dfs(int v)
{
	int &next = adjList[v];
	if (visited[next]) return;
	visited[next] = true;
	dfs(next);
}

int solve()
{
	memset(visited, 0, sizeof(visited));

	int ret = 0;
	for (int v = 1; v <= N; v++)
	{
		if (visited[v]) continue;
		ret++;
		visited[v] = true;
		dfs(v);
	}

	return ret;
}

int main(void)
{
	scanf("%d", &T);
	for (int i = 0; i < T; i++)
	{
		scanf("%d", &N);
		for (int v = 1; v <= N; v++)
			scanf("%d", &adjList[v]);

		printf("%d\n", solve());
	}

	return 0;
}