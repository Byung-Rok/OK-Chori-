import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N,M,arr[][],dp[][];
	static Queue<int[]> q;
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static int result;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		dp= new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
				dp[i][j]=-2;
			}
		} // make map;
		q = new LinkedList<>();
		result = goDFS(0,0);
		System.out.println(result);
	}

	private static int goDFS(int x,int y) {
		if(x==N-1 && y==M-1) {
			return 1;
		}
		if(dp[x][y]!=-2) return dp[x][y];
		dp[x][y]=0;
		for(int a=0;a<4;a++) {
			int qx = x+dx[a];
			int qy = y+dy[a];
			if(qx<0||qy<0||qx>=N||qy>=M)continue;
			if(arr[x][y]<=arr[qx][qy]) continue;
			dp[x][y]+=goDFS(qx,qy);
		}
		return dp[x][y];
	}
}
