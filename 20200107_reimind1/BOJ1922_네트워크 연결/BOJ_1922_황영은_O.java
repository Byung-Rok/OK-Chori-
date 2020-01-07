import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N, M;
	static int[] parent;
	static int cnt, cost;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
		M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++)
			parent[i] = i;

		TreeSet<Point> tree = new TreeSet<>();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken()); // 비용
			tree.add(new Point(a, b, cost));
		}
		
		for(Point p : tree) {
			unionSet(p);
			if(cnt == N - 1) break;
		}
		System.out.println(cost);
	}

	static void unionSet(Point p) {
		int a = findSet(p.a);
		int b = findSet(p.b);

		if (a != b) {
			parent[b] = a;
			cnt++;
			cost += p.cost;
		}
	}

	static int findSet(int a) {
		if (parent[a] == a)
			return a;
		else {
			int temp = findSet(parent[a]);
			parent[a] = temp;
			return temp;
		}
	}

	static class Point implements Comparable<Point> {
		int a;
		int b;
		int cost;

		public Point(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			return this.cost > o.cost ? 1 : -1;
		}

	}
}
