import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int v, e, start, dp[], temp[], val, dest;
	static List<int[]> nodes[];
	static boolean visited[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		v = Integer.parseInt(br.readLine());
		e = Integer.parseInt(br.readLine());
		nodes = new List[v+1];
		dp = new int[v+1];
		visited = new boolean[v+1];
		for(int i=1; i<=v; i++) {
			nodes[i] = new ArrayList<>();
			dp[i] = Integer.MAX_VALUE;
		}
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[Integer.parseInt(st.nextToken())].add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		dest = Integer.parseInt(st.nextToken());
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1]-o2[1];
			}
		});
		pq.offer(new int[] {start, 0});
		dp[start]=0;
		while(!pq.isEmpty()) {
			temp = pq.poll();
			if(visited[temp[0]]) continue;
			visited[temp[0]] = true;
			for(int[] next : nodes[temp[0]]) {
				val = temp[1]+next[1];
				if(dp[next[0]]<=val) continue;
				dp[next[0]] = val;
				pq.offer(new int[] {next[0], val});
			}
		}
		System.out.println(dp[dest]);
	}
}
