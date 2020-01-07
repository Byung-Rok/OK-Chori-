import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;
	static int [][] MAP;
	static boolean [][] visited;
	static int [][] board;
	static int res=Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		MAP = new int[N][N];
		visited = new boolean[N][N];
		board = new int[N][N];
		String [] input;

		for(int i=0;i<N;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(input[j]);
			}
		}

		//대륙 외곽 표시+넘버링
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(board[i][j] == 1) {
					numberingOfisland(new position(i,j,0));
					num++;
				}
			}
		}
		for(int n=2; n<=num; n++) {
			ArrayList<position> list = new ArrayList<>();
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(board[i][j] == n && check(i,j)) {
						list.add(new position(i,j,0));
					}
				}
			}

			for(int s=0; s<list.size(); s++) {
				sol(list.get(s));
			}
		}
		System.out.println(res);
	}
	static boolean check(int row, int column) {
		boolean flag = false;
		for(int dir=0; dir<4; dir++) {
			int tRow = row +dy[dir];
			int tColumn = column +dx[dir];

			if(tRow<0 || tColumn <0 || tRow >=N || tColumn>=N) {
				continue;
			}

			if(board[tRow][tColumn] == 0) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	static void sol(position start) {
		Queue<position> q = new LinkedList<>();
		q.offer(start);
		boolean [][] visited = new boolean[N][N];
		int myNum = board[start.row][start.column];
		visited[start.row][start.column] = true;

		while(!q.isEmpty()) {
			position cur = q.poll();
			
			for(int dir=0; dir<4; dir++) {
				position next = new position(cur.row+dy[dir], cur.column+dx[dir], cur.count+1);
				if(next.row <0 || next.column<0 || next.row>=N || next.column>=N) {
					continue;
				}
				if(board[next.row][next.column]==myNum) {
					continue;
				}
				if(board[next.row][next.column] ==0 && !visited[next.row][next.column]) {
					//바다면 한칸씩 이동
					q.offer(next);
					visited[next.row][next.column] = true;
				}
				if(board[next.row][next.column] != myNum && board[next.row][next.column]>0) {
					//내가 아닌 다른 섬을 만나면
					if(res>next.count-1) {
						res = next.count-1;
					
					}
				}
			}
		}
	}


	static int [] dy = {-1,0,1,0};
	static int [] dx = {0,1,0,-1};
	static int num=1;
	static void numberingOfisland(position start) {
		Queue<position> q = new LinkedList<>();
		boolean [][] visited = new boolean[N][N];
		q.offer(start);
		visited[start.row][start.column] = true;

		while(!q.isEmpty()) {
			position cur = q.poll();
			board[cur.row][cur.column] = num;

			for(int dir=0; dir<4; dir++) {
				position next = new position(cur.row+dy[dir], cur.column+dx[dir], 0);

				if(next.row<0 || next.column<0 || next.row>=N || next.column>=N) {
					continue;
				}

				if(!visited[next.row][next.column] && board[next.row][next.column]==1) {
					q.offer(next);
					visited[next.row][next.column]=  true;
				}
			}
		}
	}
	static class position{
		int row;
		int column;
		int count;
		position(int row, int column, int count){
			this.row = row;
			this.column = column;
			this.count = count;
		}
	}

}
