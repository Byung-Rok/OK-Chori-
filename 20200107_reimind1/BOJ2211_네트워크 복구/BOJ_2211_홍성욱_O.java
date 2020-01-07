import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static int M;
	static ArrayList<node>[] list; 
	static int[] cost;
	static int start=1;
	static int end;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String [] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		list = new ArrayList[N+1];
		cost = new int[N+1];
		end = N;
		for(int i=0;i<=N;i++) {
			list[i] = new ArrayList<>();
			cost[i] = Integer.MAX_VALUE;
		}
		
		for(int i=0;i<M;i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			list[a].add(new node(a,b,c));
			list[b].add(new node(b,a,c));
		}
		sol();
	}
	public static void sol() {
		PriorityQueue<node> q = new PriorityQueue<>(new Comparator<node>() {

			@Override
			public int compare(node o1, node o2) {
				// TODO Auto-generated method stub
				return o1.cost-o2.cost;
			}
		});
		
		
		ArrayList<node> visited = new ArrayList<>();
		q.offer(new node(0, start, 0));
		
		
		while(!q.isEmpty()) {
			node cur = q.poll();
			
			if(cost[cur.v2] != Integer.MAX_VALUE) continue;
			cost[cur.v2] = cur.cost; 
			
			if(cur.v1!=0) {
				visited.add(cur);
			}
			for(int i=0;i<list[cur.v2].size();i++) {
				node next = list[cur.v2].get(i);
				if(cost[next.v2] !=Integer.MAX_VALUE) continue;
				
				
					q.offer(new node(cur.v2, next.v2,  cur.cost+next.cost));
				
			}
		}
		System.out.println(visited.size());
		for(int i=0;i<visited.size();i++) {
			System.out.println(visited.get(i).v1+" "+visited.get(i).v2);
		}
	}
	
	public static class node{
		int v1;
		int v2;
		int cost;

		public node(int v1, int v2, int cost) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.cost = cost;
		}
	}
}
