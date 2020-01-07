import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int testcase, v, e, node1, node2, mark[], color;
	static boolean result;
	static List<Integer> nodes[];
	static Queue<Integer> q;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		q = new LinkedList<>();
		testcase = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=testcase; tc++) {
			color=1;
			result = false;
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			mark = new int[v+1];
			e = Integer.parseInt(st.nextToken());
			nodes = new List[v+1];
			for(int i=1; i<=v; i++) nodes[i] = new ArrayList<>();
			for(int i=0; i<e; i++) {
				st = new StringTokenizer(br.readLine());
				node1 = Integer.parseInt(st.nextToken());
				node2 = Integer.parseInt(st.nextToken());
				nodes[node1].add(node2);
				nodes[node2].add(node1);
			}
			for(int i=1; i<=v; i++) {
				if(mark[i]==0) {
					if(!bfs(i)) {
						result = true;
						break;
					}
				}
			}
			System.out.println(result ? "NO" : "YES");
		}
	}
	private static boolean bfs(int start) {
		q.clear();
		q.offer(start);
		mark[start] = color;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(Integer next : nodes[cur]) {
				if(mark[next]==0) {
					mark[next]=mark[cur]+1;
					q.offer(next);
				}else if(mark[next]%2 == mark[cur]%2) {
					return false;
				}
			}
		}
		return true;
	}
}
