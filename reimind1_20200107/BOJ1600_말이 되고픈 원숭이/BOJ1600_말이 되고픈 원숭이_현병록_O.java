import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int memo[][][], k, sero, garo, temp[];
	static boolean map[][];
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		garo = Integer.parseInt(st.nextToken());
		sero = Integer.parseInt(st.nextToken());
		map = new boolean[sero][garo];
		memo = new int[sero][garo][k+1];
		for(int i=0; i<sero; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<garo; j++) {
				if(Integer.parseInt(st.nextToken())==1) map[i][j]=true;
				Arrays.fill(memo[i][j], Integer.MAX_VALUE);
			}
		}
		System.out.println(bfs());
	}
	static int[] dy= {-1,0,1,0}, dx= {0,1,0,-1}, hy= {-1,-2,-2,-1,1,2,2,1}, hx= {-2,-1,1,2,2,1,-1,-2};
	static int ny, nx, nm;
	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0, 0});
		memo[0][0][0] = 0;
		while(!q.isEmpty()) {
			temp = q.poll();
			if(temp[0]==sero-1 && temp[1]==garo-1) return memo[temp[0]][temp[1]][temp[2]];
			if(temp[2]<k) {
				for(int d=0; d<8; d++) {
					ny = temp[0]+hy[d];
					nx = temp[1]+hx[d];
					nm = temp[2]+1;
					if(ny<0||nx<0||ny>=sero||nx>=garo||map[ny][nx]||memo[temp[0]][temp[1]][temp[2]]+1>=memo[ny][nx][nm]) continue;
					memo[ny][nx][nm] = memo[temp[0]][temp[1]][temp[2]]+1;
					q.offer(new int[] {ny, nx, nm});
				}
			}
			for(int d=0; d<4; d++) {
				ny = temp[0]+dy[d];
				nx = temp[1]+dx[d];
				if(ny<0||nx<0||ny>=sero||nx>=garo||map[ny][nx]||memo[temp[0]][temp[1]][temp[2]]+1>=memo[ny][nx][temp[2]]) continue;
				memo[ny][nx][temp[2]] = memo[temp[0]][temp[1]][temp[2]]+1;
				q.offer(new int[] {ny, nx, temp[2]});
			}
		}
		return -1;
	}
}
