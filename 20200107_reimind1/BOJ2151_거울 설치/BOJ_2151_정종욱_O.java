import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[][] arr;
	static int[][] visit;
	static String str;
	static int N,endx,endy,stx,sty,min;
	static int dx[] = {0,1,0,-1}; // 우하좌상
	static int dy[] = {1,0,-1,0};
	static Queue<int[]> q = new LinkedList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		arr = new char[N][N];
		visit = new int[N][N];
		min = 54321;
		
		for(int i=0;i<N;i++) {
			str = br.readLine();
			for(int j=0;j<N;j++) {
				arr[i][j] = str.charAt(j);
				if(arr[i][j]=='#') {
					q.offer(new int[] {i,j,0}); // x,y,거울만난횟수
					visit[i][j]=-1;
				}else {
					visit[i][j]=54321;
				}
			}
		}// make map;
		int temp[] = q.poll(); // 입구 하나만 사용, 다른한쪽은 버림
		endx = temp[0];
		endy = temp[1];
		doorDir(); // 처음 문에서 나아갈 방향 설정
		bfs();
		System.out.println(min);
	}
	// 우하좌상

	private static void doorDir() {
		int temp[] = q.poll();
		for(int a=0;a<4;a++) {
			stx = temp[0];
			sty = temp[1];
			int qx = temp[0]+dx[a];
			int qy = temp[1]+dy[a];
			if(qx>=N||qy>=N||qx<0||qy<0||arr[qx][qy]=='*') continue;
			if(arr[qx][qy]=='!') {
				if(a==0 || a==2) {
					q.offer(new int[] {qx,qy,1,1}); // x,y,거울만난횟수, 방향
					q.offer(new int[] {qx,qy,1,3});
				}else {
					q.offer(new int[] {qx,qy,1,0}); 
					q.offer(new int[] {qx,qy,1,2});
				}
			} else if(visit[qx][qy]==-1 && qx==endx && qy==endy) {
				if(temp[2]<min) min=temp[2]; // 도착시 최소값 저장
			}
			visit[qx][qy]-=1;
			q.offer(new int[] {qx,qy,0,a});
		}
	}

	private static void bfs() {
		while(!q.isEmpty()) {
			int temp[] = q.poll(); // x,y,거울개수,방향
			int qx = temp[0]+dx[temp[3]];
			int qy = temp[1]+dy[temp[3]];
			if(qx>=N||qy>=N||qx<0||qy<0||arr[qx][qy]=='*') continue;
			if(arr[qx][qy]=='!') {
				if(temp[2]+1<visit[qx][qy]) {
					visit[qx][qy]=temp[2]+1;
					q.offer(new int[] {qx,qy,temp[2]+1,(temp[3]+1)%4}); // 90도 방향
					q.offer(new int[] {qx,qy,temp[2]+1,(temp[3]+3)%4}); // 90도 방향
				}
			}else if(visit[qx][qy]==-1 && qx==endx && qy==endy) {
				if(temp[2]<min) min=temp[2]; // 도착시 최소값 저장
			}
			q.offer(new int[] {qx,qy,temp[2],temp[3]});	//그대로 진행
		}
	}
}
