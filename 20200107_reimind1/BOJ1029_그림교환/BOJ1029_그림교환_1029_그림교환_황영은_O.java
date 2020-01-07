import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, max, result;
	static int[][] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		max = 1 << N;
		dp = new int[N][max];
		result = 1;
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			for(int j = 0; j < N; j++) {
				arr[i][j] = temp.charAt(j) - '0';
			}
		}
		// 1번 아티스트부터 시작해야됨 ~ !
		dfs(1, 0, 0, 1);
		System.out.println(result);
	}
	private static void dfs(int v, int idx, int cost, int cnt) {
		if(dp[idx][v] >= cnt) return;
		boolean isLast = false;
		for(int i = 0; i < N; i++) {
			if((v & (1 << i)) > 0 || arr[idx][i] < cost) {
				continue;
			}
			isLast = true;
			dp[idx][v] = cnt;
			dfs((v | (1 << i)), i, arr[idx][i], cnt + 1);
		}
		if(!isLast) result = Math.max(result, cnt);
	}
}
