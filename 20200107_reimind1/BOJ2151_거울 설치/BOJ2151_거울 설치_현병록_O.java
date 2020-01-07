import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	static int size, memoMirror[][][], temp[];
	static char map[][];
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		map = new char[size][size];
		memoMirror = new int[size][size][4];
		for(int i=0; i<size; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<size; j++)
				Arrays.fill(memoMirror[i][j], Integer.MAX_VALUE);
		}
		here : for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map[i][j]=='#') {
					map[i][j]='*';
					System.out.println(search(i,j));
					break here;
				}
			}
		}
		
	}
	static int[] dy= {-1,0,1,0}, dx= {0,1,0,-1};
	/**
	 * 다른 문을 찾아서 탐색하는 함수
	 * @param sy - 시작 y위치
	 * @param sx - 시작 x위치
	 * @return 도착하기위한 최소한의 거울의 수
	 */
	private static int search(int sy, int sx) {
		int ny, nx, nm;
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
		});
		for(int i=0; i<4; i++) {
			pq.offer(new int[] {sy, sx, 0, i}); //y좌표, x좌표, 지금까지 만난 거울의 수, 가고있는방향
		}
		Arrays.fill(memoMirror[sy][sx], 0);
		while(!pq.isEmpty()) {
			temp = pq.poll();
			if(map[temp[0]][temp[1]]=='#') {
				return temp[2];
			}
			if(map[temp[0]][temp[1]]=='!') {
				//현재 위치가 거울일 때
				for(int d=0; d<4; d++) {
					if(((temp[3]+2)%4)==d) continue;
					ny = temp[0]+dy[d];
					nx = temp[1]+dx[d];
					nm = temp[3]==d ? temp[2] : temp[2]+1;
					if(ny<0||nx<0||ny>=size||nx>=size||map[ny][nx]=='*') continue;
					if(memoMirror[ny][nx][d] <= nm) continue;
					pq.offer(new int[] {ny, nx, nm, d});
					memoMirror[ny][nx][d] = nm;
				}
			}else {
				//거울이 아니라서 직진만 하면될 때
				ny = temp[0]+dy[temp[3]];
				nx = temp[1]+dx[temp[3]];
				if(ny<0||nx<0||ny>=size||nx>=size||map[ny][nx]=='*') continue;
				if(memoMirror[ny][nx][temp[3]]<=temp[2]) continue;
				pq.offer(new int[] {ny, nx, temp[2], temp[3]});
				memoMirror[ny][nx][temp[3]] = temp[2];
			}
		}
		return 0;
	}
}
