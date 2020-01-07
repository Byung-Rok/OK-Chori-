/*
	2019-12-26

	DFS

	Time	:	0 ms
	Memory	:	2220 kb
*/

#include <cstdio>
#include <cmath>

int A, P, visited[236196 + 1] = { 0 };

void dfs(int v)
{
	if (visited[v] == 2)
		return;

	visited[v]++;

	int dn = 0;
	while (v > 0)
	{
		dn += (int)pow(v % 10, P);
		v /= 10;
	}

	dfs(dn);
}

int solve()
{
	dfs(A);

	int count = 0;
	for (int v = 0; v <= 236196; v++)
		if (visited[v] == 1)
			count++;

	return count;
}

int main(void)
{
	scanf("%d %d", &A, &P);
	printf("%d\n", solve());

	return 0;
}