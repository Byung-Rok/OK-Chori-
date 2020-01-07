import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int v, e, temp[], result, cnt, start, dest, value;
	static boolean visited[];
	static PriorityQueue<int[]> pq;
	static List<int[]> list[];
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		v = Integer.parseInt(br.readLine());
		e = Integer.parseInt(br.readLine());
		visited = new boolean[v+1];
		list = new List[v+1];
		for(int i=1; i<=v; i++) list[i] = new ArrayList<>();
		pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1]-o2[1];
			}
		});
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			dest = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());
			list[start].add(new int[] {dest, value});
			list[dest].add(new int[] {start, value});
		}
		for(int[] next : list[1])
			pq.offer(new int[] {next[0], next[1]});
		visited[1] = true;
		while(!pq.isEmpty()) {
			temp = pq.poll();
			if(visited[temp[0]]) continue;
			visited[temp[0]] = true;
			result+=temp[1];
			if(++cnt==v-1) break;
			for(int[] next : list[temp[0]]) {
				pq.offer(new int[] {next[0], next[1]});
			}
		}
		System.out.println(result);
	}
}
