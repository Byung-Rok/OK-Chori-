import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int size, map[][], color, temp[], ny, nx, min;
	static boolean visited[][];
	static Queue<int[]> q;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		size = Integer.parseInt(br.readLine());
		map = new int[size][size];
		visited = new boolean[size][size];
		q = new LinkedList<>();
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<size; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		color=1;
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map[i][j]==1	&& !visited[i][j]) {
					numbering(i,j);					
					color++;
				}
			}
		}
		color--;
		min = Integer.MAX_VALUE;
		for(int i=1; i<color; i++) {
			min = Math.min(min, makeBridge(i));
		}
		System.out.println(min);
	}
	/**
	 * @param num - 체크할 섬의 번호
	 * @return 그 섬에서 만든 다리 중 가장 짧은 다리의 길이
	 */
	private static int makeBridge(int num) {
		int minDist = 0, qSize;
		q.clear();
		for(int i=0; i<size; i++) Arrays.fill(visited[i], false);
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map[i][j]==num) {
					visited[i][j]=true;
					q.offer(new int[] {i,j});
				}
			}
		}
		
		while(!q.isEmpty()) {
			qSize = q.size();
			minDist++;
			if(min<minDist) return Integer.MAX_VALUE;
			for(int i=0; i<qSize; i++) {
				temp = q.poll();
				for(int d=0; d<4; d++) {
					ny = temp[0]+dy[d];
					nx = temp[1]+dx[d];
					if(ny<0||nx<0||ny>=size||nx>=size||map[ny][nx]==num||visited[ny][nx]) continue;
					if(map[ny][nx]!=0) {
						//다른 섬을 만나면
						return minDist-1;
					}
					q.offer(new int[] {ny, nx});
					visited[ny][nx]=true;
				}
			}
		}
		return -1;
	}
	/**
	 * @param y - 시작위치의 행
	 * @param x - 시작위치의 열
	 */
	static int[] dy= {-1,0,1,0}, dx= {0,1,0,-1};
	private static void numbering(int y, int x) {
		q.offer(new int[] {y,x});
		map[y][x] = color;
		visited[y][x]=true;
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int d=0; d<4; d++) {
				 ny = temp[0] + dy[d];
				 nx = temp[1] + dx[d];
				 if(ny<0||nx<0||ny>=size||nx>=size||visited[ny][nx]||map[ny][nx]==0) continue;
				 map[ny][nx] = color;
				 visited[ny][nx]=true;
				 q.offer(new int[] {ny,nx});
			}
		}
	}
}
