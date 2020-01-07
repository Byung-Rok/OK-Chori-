import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, s, e, v, memo[], temp[], come[];
	static List<int[]> nodes[], result;
	static boolean visited[], linked[][];
	static final int superComputer = 1;
	static StringBuilder sb;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken()); //노드의 갯수
		m = Integer.parseInt(st.nextToken()); // 간선의 갯수
		nodes = new List[n+1];
		visited = new boolean[n+1];
		linked = new boolean[n+1][n+1];
		memo = new int[n+1];
		come = new int[n+1];
		for(int i=1; i<=n; i++) nodes[i] = new ArrayList<>();
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			nodes[s].add(new int[] {e, v});
			nodes[e].add(new int[] {s, v});
		}
		Arrays.fill(memo, Integer.MAX_VALUE);
		dijkstra();
		checkEdge();
		System.out.println(sb.toString());
	}
	private static void checkEdge() {
		int cnt=0;
		for(int i=1; i<=n; i++) {
			int cur = i;
			while(come[cur]!=0) {
				if(!linked[cur][come[cur]]) {
					linked[cur][come[cur]] = true;
					linked[come[cur]][cur] = true;
					cnt++;
					sb.append(cur).append(" ").append(come[cur]).append("\n");
				}
				cur = come[cur];
			}
		}
		sb.insert(0, cnt+"\n");
	}
	private static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1]-o2[1];
			}
		});
		memo[superComputer] = 0;
		pq.offer(new int[] {superComputer, 0});
		int val;
		while(!pq.isEmpty()) {
			temp = pq.poll();
			if(visited[temp[0]]) continue;
			visited[temp[0]] = true;
			for(int[] next : nodes[temp[0]]) {
				val = temp[1]+next[1];
				if(memo[next[0]]<=val) continue;
				memo[next[0]] = val;
				come[next[0]]=temp[0];
				pq.offer(new int[] {next[0], val});
			}
		}
	}
}
