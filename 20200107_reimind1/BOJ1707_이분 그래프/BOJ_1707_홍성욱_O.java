import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static ArrayList<Integer> [] MAP;
	static int V;
	static int E;
	static int [] group;	// 1은 그룹1,  -1는 그룹2,  3은 모르는 상태
	static int res;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		String [] input;
		for(int k=0; k<K ; k++) {
			res=1;
			input = br.readLine().split(" ");
			
			V = Integer.parseInt(input[0]);
			E = Integer.parseInt(input[1]);
			
			MAP = new ArrayList[V+1];
			group = new int[V+1];
			
			for(int v=1; v<=V; v++) {
				MAP[v] = new ArrayList<>();
			}
			for(int e=0 ; e<E ; e++) {
				input = br.readLine().split(" ");
				int a = Integer.parseInt(input[0]);
				int b = Integer.parseInt(input[1]);
				
				MAP[a].add(b);
				MAP[b].add(a);
			}
			
			
			for(int i=1; i<=V; i++) {
				if(group[i] == 0) {
					sol(i);
				}
			}
			System.out.println(res==1 ? "YES" : "NO");
		}

	}
	public static void sol(int vertex) {
		
		Queue<position> q = new LinkedList<>();
		
		position cur = new position(vertex,1);
		if(group[cur.data]==0) {
			group[cur.data] = 1;
		}
		q.offer(cur);
		
		
		while(!q.isEmpty()) {
			cur = q.poll();
			
			for(int i=0; i<MAP[cur.data].size(); i++) {
				
					int idx = MAP[cur.data].get(i);
					if(group[idx] == 0) {
						//그룹 분류 안되있으면
						group[idx] = -group[cur.data];
						q.offer(new position(idx, cur.phase+1));
					}
					else {
						//그룹 분류 되있는데,,
						
						if(group[cur.data] == group[idx]) {
							//만약 연결된 친구가 나랑 같은 그룹이면,,
							res=0;
							return;
						}
					}

				
			}
		}
	}
	static class position{
		int data;
		int phase;
		position(int data, int phase){
			this.data = data;
			this.phase = phase;
		}
	}
	
}
