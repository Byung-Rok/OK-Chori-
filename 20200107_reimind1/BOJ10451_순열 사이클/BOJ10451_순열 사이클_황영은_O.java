import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static int T, N, cnt;
	static int[] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			visited = new boolean[N + 1];
			cnt = 0;
			for(int i = 1; i <= N; i++) {
				if(visited[i]) continue;
				int temp = arr[i];
				visited[i] = true;
				while(true) {
					if(visited[temp]) {
						cnt++;
						break;
					}
					visited[temp] = true;
					temp = arr[temp];
				}
			}
			bw.write(String.valueOf(cnt));
			bw.write("\n");
		}
		bw.flush();
	}

}
