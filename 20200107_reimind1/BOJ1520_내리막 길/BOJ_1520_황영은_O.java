import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int M, N;
	static int[][] map, memo;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		memo = new int[M][N];
		for(int i = 0; i < M; i++) Arrays.fill(memo[i], -1);
		System.out.println(dfs(0, 0));
	}

	private static int dfs(int x, int y) {
		if(x == M - 1 && y == N - 1) return 1;
		if(memo[x][y] != -1) return memo[x][y];
		
		int temp = map[x][y];
		memo[x][y] = 0;
		for(int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			
			if(tx < 0 || ty < 0 || tx >= M || ty >= N) continue;
			if(temp <= map[tx][ty]) continue;
			
			memo[x][y] += dfs(tx, ty);
		}
		return memo[x][y];
	}
}
