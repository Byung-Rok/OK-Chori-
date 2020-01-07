import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int testcase, size, cnt, link[];
	static boolean visited[];
	static List<Integer> list;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		testcase = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for(int tc=1; tc<=testcase; tc++) {
			size = Integer.parseInt(br.readLine());
			link = new int[size+1];
			visited = new boolean[size+1];
			list.clear();
			cnt=0;
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=size; i++) link[i]=Integer.parseInt(st.nextToken());
			for(int i=1; i<=size; i++) {
				if(visited[i]) continue;
				int cur = i;
				while(!visited[cur]) {
					list.add(cur);
					visited[cur]=true;
					cur = link[cur];
				}
				if(list.contains(cur)) {
					cnt+=list.indexOf(cur);
				}else {
					cnt+=list.size();
				}
				list.clear();
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
}
