import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] maze;
	static Point minsik;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new char[N][M];
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = temp.charAt(j);
				if (maze[i][j] == '0')
					minsik = new Point(i, j, 0);
			}
		}
		Queue<Point> q = new LinkedList<>();
		q.add(minsik);

		boolean check = false;
		int cnt = 1;
		int[][][] visited = new int[N][M][1 << 6];

		aa: while (!q.isEmpty()) {
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Point temp = q.poll();
				
				for (int j = 0; j < 4; j++) {
					int tx = temp.x + dx[j];
					int ty = temp.y + dy[j];

					if (tx < 0 || ty < 0 || tx >= N || ty >= M) continue;
					char next = maze[tx][ty];
					if (next == '1') {
						check = true;
						break aa;
					}
					if (('A' <= next && next <= 'Z' && ((temp.al & (1 << (next - 'A'))) == 0)) || (next == '#')) {
						continue;
					}
					int al = temp.al;
					if ('a' <= next && next <= 'z') {
						al = temp.al | (1 << (next - 'a'));
					}
					if(visited[tx][ty][al] == 1) continue;
					visited[tx][ty][al] = 1;
					
					q.add(new Point(tx, ty, al));
				}
			}
			cnt++;
		}
		System.out.println(check ? cnt : -1);
	}

	static class Point {
		int x, y, al;

		public Point(int x, int y, int al) {
			this.x = x;
			this.y = y;
			this.al = al;
		}

	}
}
