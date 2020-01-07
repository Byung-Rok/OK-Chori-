/*
	2019-12-25
	
	DFS, Cycle
	
	Time		:	848 ms
	Memory		:	4892 kb	
*/

#include <cstdio>
#include <cstring>

int T, n, adj[100000 + 1], visited[100000 + 1];

void dfs(int v)
{
	// 사이클이 아닌 정점
	if (visited[adj[v]] == -1) 
		return;

	// 사이클인 정점
	if (visited[adj[v]] == 2) 
		return;

	visited[adj[v]]++;
	dfs(adj[v]);
	if (visited[adj[v]] == 1)
		visited[adj[v]] = -1;
}

int solve()
{
	for (int v = 1; v <= n; v++)
	{
		if (!visited[v])
		{
			visited[v]++;
			dfs(v);
			if (visited[v] == 1) 
				visited[v] = -1;
		}
	}

	// 사이클이 아닌 정점을 모두 헤아린다.
	int count = 0;
	for (int v = 1; v <= n; v++)
		if (visited[v] == -1)
			count++;

	return count;
}

int main(void)
{
	scanf("%d", &T);
	for (int tc = 0; tc < T; tc++)
	{
		scanf("%d", &n);

		for (int i = 1; i <= n; i++)
			scanf("%d", &adj[i]);

		memset(visited, 0, sizeof(visited));
		printf("%d\n", solve());
	}

	return 0;
}