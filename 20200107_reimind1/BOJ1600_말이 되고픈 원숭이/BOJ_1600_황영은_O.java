import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K, W, H;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	static int[] hx = { -2, -1, 1, 2, 2, 1, -1, 2, -1 }, hy = { 1, 2, 2, 1, -1, -2, -2, -1 }; // 8

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken()); // 가로길이 (cols)
		H = Integer.parseInt(st.nextToken()); // 세로길이 (rows)
		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Queue<Point> q = new LinkedList<>();
		int[][][] visited = new int[H][W][K + 1];
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				Arrays.fill(visited[i][j], 	Integer.MAX_VALUE);
			}
		}
		q.add(new Point(0, 0, 0));
		int cnt = 0;
		boolean check = false;
		aa : while (!q.isEmpty()) {
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Point temp = q.poll();

				for (int j = 0; j < 4; j++) {
					int tx = temp.x + dx[j];
					int ty = temp.y + dy[j];

					if (tx < 0 || ty < 0 || tx >= H || ty >= W || map[tx][ty] == 1) continue;
					if(tx == H - 1 && ty == W - 1) {
						check = true;
						break aa;
					}
					if(visited[tx][ty][temp.k] <= cnt + 1) continue;
					visited[tx][ty][temp.k] = cnt + 1;
					q.add(new Point(tx, ty, temp.k));
				}
				
				for(int j = 0; j < 8; j++) {
					int tx = temp.x + hx[j];
					int ty = temp.y + hy[j];
					
					if (tx < 0 || ty < 0 || tx >= H || ty >= W || map[tx][ty] == 1) continue;
					if(temp.k + 1 > K) continue;
					if(tx == H - 1 && ty == W - 1) {
						check = true;
						break aa;
					}
					if(visited[tx][ty][temp.k + 1] <= cnt + 1) continue;
					visited[tx][ty][temp.k + 1] = cnt + 1;
					q.add(new Point(tx, ty, temp.k + 1));
				}
			}
			cnt++;
		}
		System.out.println(check ? (cnt + 1) : -1);
	}

	static class Point {
		int x, y, k;

		public Point(int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}
}
