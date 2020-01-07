import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_내리막길_1520_박정호_O {
	static int R, C;
	static int[][] map;
	static int[][] memo;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().trim().split(" ");
		R = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);
		map = new int[R][C];
		memo = new int[R][C];

		for (int i = 0; i < R; ++i) {
			s = br.readLine().trim().split(" ");
			for (int j = 0; j < C; ++j) {
				map[i][j] = Integer.parseInt(s[j]);
				memo[i][j] = -1; // 모두 -1로 초기화
			}
		}
		System.out.println(go(0, 0));
	}

	private static int go(int r, int c) {
		if (r == R - 1 && c == C - 1) return 1;
		if (memo[r][c] != -1) return memo[r][c];

		memo[r][c] = 0;
		for (int i = 0; i < 4; ++i) {
			int tr = r + dr[i];
			int tc = c + dc[i];

			if (tr < 0 || tc < 0 || tr >= R || tc >= C || map[r][c] <= map[tr][tc])	continue;
			memo[r][c] += go(tr, tc);
		}
		return memo[r][c];
	}
}
