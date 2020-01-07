import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_달이차오른다가자_1194_박정호_O {
	static int R, C;
	static Character[][] map;
	static int cr, cc;
	static boolean[][][] visit;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static class Pair {
		int r;
		int c;
		int key;

		public Pair(int r, int c, int key) {
			this.r = r;
			this.c = c;
			this.key = key;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		R = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);
		map = new Character[R][C];
		visit = new boolean[R][C][1 << 6];

		for (int i = 0; i < R; ++i) {
			s = br.readLine().split("");
			for (int j = 0; j < C; ++j) {
				map[i][j] = s[j].charAt(0);
				if (map[i][j] == '0') {
					cr = i;
					cc = j;
					map[i][j] = '.';
				}
			}
		}

		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(cr, cc, 0));
		visit[cr][cc][0] = true;
		Pair p = null;
		int level = 0;
		boolean flag = false;
		here: while (!q.isEmpty()) {
			int qSize = q.size();
			for (int i = 0; i < qSize; ++i) {
				p = q.poll();
				int r = p.r;
				int c = p.c;
				int key = p.key;
				int tr, tc;
				for (int j = 0; j < 4; ++j) {
					tr = r + dr[j];
					tc = c + dc[j];
					if (tr < 0 || tc < 0 || tr >= R || tc >= C || visit[tr][tc][key])
						continue;
					if (map[tr][tc] == '.') {
						visit[tr][tc][key] = true;
						q.add(new Pair(tr, tc, key));
					} else if (map[tr][tc] == '#') {
						visit[tr][tc][key] = true;
						continue;
					} else if (map[tr][tc] == '1') {
						level++;
						flag = true;
						break here;
					} else if (map[tr][tc] == 'a') {
						int tempKey = key | (1 << ('a' - 'a'));
						visit[tr][tc][tempKey] = true;
						q.add(new Pair(tr, tc, tempKey));
					} else if (map[tr][tc] == 'b') {
						int tempKey = key | (1 << ('b' - 'a'));
						visit[tr][tc][tempKey] = true;
						q.add(new Pair(tr, tc, tempKey));
					} else if (map[tr][tc] == 'c') {
						int tempKey = key | (1 << ('c' - 'a'));
						visit[tr][tc][tempKey] = true;
						q.add(new Pair(tr, tc, tempKey));
					} else if (map[tr][tc] == 'd') {
						int tempKey = key | (1 << ('d' - 'a'));
						visit[tr][tc][tempKey] = true;
						q.add(new Pair(tr, tc, tempKey));
					} else if (map[tr][tc] == 'e') {
						int tempKey = key | (1 << ('e' - 'a'));
						visit[tr][tc][tempKey] = true;
						q.add(new Pair(tr, tc, tempKey));
					} else if (map[tr][tc] == 'f') {
						int tempKey = key | (1 << ('f' - 'a'));
						visit[tr][tc][tempKey] = true;
						q.add(new Pair(tr, tc, tempKey));
					} else if (map[tr][tc] == 'A') {
						if ((key & 1 << ('a' - 'a')) == 0)
							continue;
						visit[tr][tc][key] = true;
						q.add(new Pair(tr, tc, key));
					} else if (map[tr][tc] == 'B') {
						if ((key & 1 << ('b' - 'a')) == 0)
							continue;
						visit[tr][tc][key] = true;
						q.add(new Pair(tr, tc, key));
					} else if (map[tr][tc] == 'C') {
						if ((key & 1 << ('c' - 'a')) == 0)
							continue;
						visit[tr][tc][key] = true;
						q.add(new Pair(tr, tc, key));
					} else if (map[tr][tc] == 'D') {
						if ((key & 1 << ('d' - 'a')) == 0)
							continue;
						visit[tr][tc][key] = true;
						q.add(new Pair(tr, tc, key));
					} else if (map[tr][tc] == 'E') {
						if ((key & 1 << ('e' - 'a')) == 0)
							continue;
						visit[tr][tc][key] = true;
						q.add(new Pair(tr, tc, key));
					} else if (map[tr][tc] == 'F') {
						if ((key & 1 << ('f' - 'a')) == 0)
							continue;
						visit[tr][tc][key] = true;
						q.add(new Pair(tr, tc, key));
					}
				}
			}
			level++;
		}
		if(flag)
			System.out.println(level);
		else
			System.out.println(-1);
	}

}
