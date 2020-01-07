import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int K, V, E, aa, bb,resultnum;
	static int[] visited;
	static ArrayList<Integer>[] list;
	static String[] result = {"YES","NO"};

	public static void main(String[] args) throws IOException {
		K = Integer.parseInt(br.readLine()); // tc

		for (int t = 1; t <= K; t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // 노드수
			E = Integer.parseInt(st.nextToken()); // 간선개수
			list = new ArrayList[V + 1];
			for (int i = 1; i < list.length; i++) {
				list[i] = new ArrayList<>();
			}
			for (int a = 0; a < E; a++) {
				st = new StringTokenizer(br.readLine());
				aa = Integer.parseInt(st.nextToken());
				bb = Integer.parseInt(st.nextToken());
				list[aa].add(bb);
				list[bb].add(aa);
			}
			visited = new int[V+1];
			visited[1] = 2;
			resultnum=0;

			go1(1);
			for(int c=2;c<=V;c++) {
				if(visited[c]==0)
					go1(c);
					
			}
			
			System.out.println(result[resultnum]);
		}
	}

	private static void go1(int gogo) {
		for(int i=0;i<list[gogo].size();i++) {
			if(visited[list[gogo].get(i)]==0) {
				visited[list[gogo].get(i)]=1;
					go2(list[gogo].get(i));
			}	else if(visited[list[gogo].get(i)]==2)
				resultnum=1;
		}
	}

	private static void go2(int gogo) {
		for(int i=0;i<list[gogo].size();i++) {
			if(visited[list[gogo].get(i)]==0) {
				visited[list[gogo].get(i)]=2;
					go1(list[gogo].get(i));
		}	else if(visited[list[gogo].get(i)]==1)
			resultnum=1;
	}
}
}
