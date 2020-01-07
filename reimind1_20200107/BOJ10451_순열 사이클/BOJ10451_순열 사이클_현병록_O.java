import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int testcase, link[], size, cnt;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		testcase = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=testcase; tc++) {
			size = Integer.parseInt(br.readLine());
			link = new int[size+1];
			cnt=0;
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=size; i++) link[i]=Integer.parseInt(st.nextToken());
			for(int i=1; i<=size; i++) {
				if(link[i]==0) continue;
				dfs(i);
				cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static void dfs(int cur) {
		if(link[cur]==0) return;
		int num = link[cur];
		link[cur]=0;
		dfs(num);
	}
}
