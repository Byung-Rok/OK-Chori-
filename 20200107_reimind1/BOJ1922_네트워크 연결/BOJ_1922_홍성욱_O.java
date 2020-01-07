import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	static int N;
	static int M;
	static int [][] cost;
	static int [] parent;
	static int res=Integer.MAX_VALUE;
	static ArrayList<node> list=new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		cost = new int[N+1][N+1];
		
		for(int i=0;i<M;i++) {
			String [] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			
			list.add(new node(a,b,c));
			cost[a][b] = c;
			cost[b][a] = c;
		}
		Collections.sort(list, new Comparator<node>() {

			@Override
			public int compare(node o1, node o2) {
				// TODO Auto-generated method stub
				return o1.cost-o2.cost;
			}
			
		});
		sol();
		
		System.out.println(res);
		
	}
	public static void sol() {
		makeSet();
		int sum=0;
		for(int i=0;i<list.size();i++) {
		
			int a  = findSet(list.get(i).v1);
			int b = findSet(list.get(i).v2);
			if(a!=b) {
				sum+=list.get(i).cost;
//				System.out.println(list.get(i).v1 + "  -  "+ list.get(i).v2);
				unionSet(a, b);
			}
		}
		if(res>sum) {
			res= sum;
		}
	}
	
	public static int findSet(int a) {
		//루트 노드는 부모 노드 번호로 자기 자신을 가진다.
		if(parent[a] == a) {
			return a;
		}else {
			int p = findSet(parent[a]);
			parent[a] = p;
			return p;
		}
		
		
	}
	public static void unionSet(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		
		if(a!=b) {
			parent[b] = a;
		}
	}
	public static void makeSet() {
		for(int i=1;i<=N;i++) {
			parent[i] =  i;
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
