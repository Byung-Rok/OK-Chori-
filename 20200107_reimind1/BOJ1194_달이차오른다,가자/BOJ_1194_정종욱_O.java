import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer sb = new StringBuffer();
	static StringTokenizer st;
	static int N,M;
	static char[][] arr;
	static boolean[][][] visit;
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,-1,0,1};
	static Queue<int[]> q;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		visit = new boolean[N][M][1<<6];
		q = new LinkedList<int[]>();
		
		for(int i=0;i<N;i++) {
			String tmp = br.readLine();
			for(int j=0;j<M;j++) {
				arr[i][j]=tmp.charAt(j);
				if(arr[i][j]=='0') {
					q.offer(new int[] {i,j,0,0});
					visit[i][j][0]=true;
				}
			}
		} // make map;
		System.out.println(bfs());
	}

	private static int bfs() {
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			if(arr[temp[0]][temp[1]]=='1') return temp[3];
			
			for(int z=0;z<4;z++) {
				int qx = temp[0]+dx[z];
				int qy = temp[1]+dy[z];
				int key = temp[2];
				if(qx<0||qy<0||qx>=N||qy>=M)continue;
				if(arr[qx][qy]=='#')continue;
				if('a'<=arr[qx][qy]&&arr[qx][qy]<='f') {
					key|=(1<<arr[qx][qy]-'a'); // or연산(합)
				}
				if('A'<=arr[qx][qy]&&arr[qx][qy]<='F') {
					if((key&(1<<(arr[qx][qy]-'A')))==0)continue;
				}
				if(visit[qx][qy][key]==true)continue;
				visit[qx][qy][key]=true;
				q.offer(new int[] {qx,qy,key,temp[3]+1});
			}
		}
		return -1;
	}
	
}
