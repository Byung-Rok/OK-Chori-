import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;
	static char[][] home;
	static Point start, end;
	static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 }; // 상우하좌

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		home = new char[N][N];
		Queue<Point> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < N; j++) {
				home[i][j] = temp.charAt(j);
				if (home[i][j] == '#') {
					if (start == null) {
						start = new Point(i, j);
						for(int k = 0; k < 4; k++) {
							q.add(new Point(i, j, 0, k));
						}
					} else
						end = new Point(i, j);
				}
			}
		}

		int[][][] visited = new int[N][N][4];
		int min = 1000;

		exit : while (!q.isEmpty()) {
			Point temp = q.poll();
			
			int tx = temp.x;
			int ty = temp.y;
			
			while(true) {
				tx += dx[temp.dir];
				ty += dy[temp.dir];
				
				if(tx < 0 || ty < 0 || tx >= N || ty >= N || home[tx][ty] == '*') continue exit;
				if(end.x == tx && end.y == ty) {
					min = Math.min(min, temp.cnt);
					continue exit;
				}
				if(home[tx][ty] == '!') break;
			}
			
			for(int i = 0; i < 4; i++) {
				if(i == (temp.dir + 2) % 4) continue;
				if(visited[tx][ty][i] != 0 && visited[tx][ty][i] <= temp.cnt) continue;
				
				if(i == temp.dir) {
					visited[tx][ty][i] = temp.cnt;
					q.add(new Point(tx, ty, temp.cnt, i));
					continue;
				}
				visited[tx][ty][i] = temp.cnt + 1;
				q.add(new Point(tx, ty, temp.cnt + 1, i));
			}
		}
		System.out.println(min);

	}

	static class Point {
		int x, y, cnt, dir;

		public Point(int x, int y, int cnt, int dir) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
