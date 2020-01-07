import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int howChain, ny, nx, temp[];
	static char map[][];
	static boolean visited[][];
	static final int sero = 12, garo = 6;
	static int[] dy= {-1,0,1,0}, dx= {0,1,0,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[sero][garo];
		for(int i=0; i<sero; i++) map[i] = br.readLine().toCharArray();
		
		while(removeBlock()) {
			howChain++;
			dropBlock();
		}
		System.out.println(howChain);
	}
	
	/**
	 *  블럭이 지워진 후 블럭을 떨어뜨리는 함수
	 */
	private static void dropBlock() {
		Queue<Character> q = new LinkedList<>();
		for(int i=0; i<garo; i++) {
			for(int j=sero-1; j>=0; j--) {
				if(map[j][i]!='.')
					q.offer(map[j][i]);
			}
			int qSize = q.size();
			for(int j=sero-1; j>=sero-qSize; j--) {
				map[j][i]=q.poll();
			}
			for(int j=sero-qSize-1; j>=0; j--) {
				map[j][i]='.';
			}
		}
	}

	/**
	 * 한번에 지워질 블럭을 지워주는 함수
	 * @return 그 턴에 지워진 블럭이 있으면 true
	 */
	private static boolean removeBlock() {
		boolean isRemove=false;
		
		visited = new boolean[sero][garo];
		for(int i=0; i<sero; i++) {
			for(int j=0; j<garo; j++) {
				if(map[i][j]!='.' && !visited[i][j] && find4block(i,j, map[i][j])) {
					isRemove = true;
				}
			}
		}
		return isRemove;
	}
	/**
	 * 이어진 블럭이 4칸 이상이면 그 블럭을 지운다.
	 * @param y - 시작 y위치
	 * @param x - 시작 x위치
	 * @param block - 탐색할 블럭의 종류
	 */
	private static boolean find4block(int y, int x, char block) {
		boolean over4 = false;
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> remove = new LinkedList<>();
		remove.offer(new int[] {y,x});
		q.offer(new int[] {y,x});
		visited[y][x] = true;
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int d=0; d<4; d++) {
				ny = temp[0]+dy[d];
				nx = temp[1]+dx[d];
				if(ny<0||nx<0||ny>=sero||nx>=garo||visited[ny][nx]||map[ny][nx]!=block) continue;
				q.offer(new int[] {ny, nx});
				remove.offer(new int[] {ny,nx});
				visited[ny][nx]=true;
			}
		}
		if(remove.size()>=4) {
			while(!remove.isEmpty()) {
				temp=remove.poll();
				map[temp[0]][temp[1]]='.';
			}
			over4 = true;
		}
		return over4;
	}
}
