import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int sero, garo, map[][], ny, nx, memo[][];
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sero = Integer.parseInt(st.nextToken());
		garo = Integer.parseInt(st.nextToken());
		map = new int[sero][garo];
		memo = new int[sero][garo];
		for(int i=0; i<sero; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<garo; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				memo[i][j]=-1;
			}
		}
		System.out.println(dfs(0,0));
	}
	static int[] dy= {-1, 0, 1, 0}, dx= {0, 1, 0, -1};
	private static int dfs(int y, int x) {
		if(y==sero-1 && x==garo-1) return 1;
		if(memo[y][x]!=-1) return memo[y][x];
		memo[y][x]=0;
		for(int d=0; d<4; d++) {
			ny = y+dy[d];
			nx = x+dx[d];
			if(ny<0||nx<0||ny>=sero||nx>=garo||map[y][x]<=map[ny][nx]) continue;
			memo[y][x] += dfs(ny,nx);
		}
		return memo[y][x];
	}
}
