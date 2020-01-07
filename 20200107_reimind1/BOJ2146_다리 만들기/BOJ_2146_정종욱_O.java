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
	static int N,isNum,brdg,min,tmp;
	static int arr[][];
	static Queue<int[]> q;
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,1,0,-1};
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		q = new LinkedList<int[]>();
		
		arr = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		isNum=1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(arr[i][j]==1) {
					isNum++;
					MakeNum(i,j);
				}
			}
		}
		min = 987654321;
		tmp = 987654321;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(arr[i][j]>0) {
					brdg--;
					goBFS(i,j);
					if(min>tmp) min=tmp;
					q.clear();
				}
			}
		}
		System.out.println(min);
	}


	private static void goBFS(int x, int y) {
		q.offer(new int[] {x,y,0});
		int now = arr[x][y];
	start:	while(!q.isEmpty()) {
			int temp[] = q.poll();
			for(int a=0;a<4;a++) {
				int qx=temp[0]+dx[a];
				int qy=temp[1]+dy[a];
				if(qx<0||qy<0||qx>=N||qy>=N)continue;
				if(arr[qx][qy]==now)continue;
				if(arr[qx][qy]>1) {
					tmp = temp[2];
					break start;
				}
				if(arr[qx][qy]==0 || arr[qx][qy]>brdg) {
					arr[qx][qy]=brdg;
					q.offer(new int[] {qx,qy,temp[2]+1});
				}
			}
		}
	}


	private static void MakeNum(int i, int j) {
		q.offer(new int[] {i,j});
		arr[i][j]=isNum;
		while(!q.isEmpty()) {
			int temp[] = q.poll();
			for(int a=0;a<4;a++) {
				int qx=temp[0]+dx[a];
				int qy=temp[1]+dy[a];
				if(qx<0||qy<0||qx>=N||qy>=N)continue;
				if(arr[qx][qy]==0)continue;
				if(arr[qx][qy]==isNum)continue;
				arr[qx][qy]=isNum;
				q.offer(new int[] {qx,qy});
			}
		}
	}
}
