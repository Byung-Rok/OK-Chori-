import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;


public class Main {
	static int [][]MAP;
	static int M;
	static int N;
	static int [][] score;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String [] input = br.readLine().split(" ");
		M = Integer.parseInt(input[0]);
		N = Integer.parseInt(input[1]);

		MAP=new int[M][N];
		score=new int[M][N];

		for(int i=0;i<M;i++) {
			input= br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				MAP[i][j] = Integer.parseInt(input[j]);
				score[i][j]=-1;
			}
		}		
		int res = sol(0,0);
		System.out.println(res);
	}
	static int [] dy = {-1,0,1,0};
	static int [] dx = {0,1,0,-1};

	public static int sol(int row, int column) {
		if(row == M-1 && column == N-1) {
			return 1;
		}

		if(score[row][column] != -1) return score[row][column];
		score[row][column]=0;
		
		for(int dir=0; dir<4; dir++) {
			int tRow = row+ dy[dir];
			int tColumn = column +dx[dir];
			if(tRow<0 || tColumn<0 || tRow>=M || tColumn>=N) {
				continue;
			}
			if(MAP[row][column] > MAP[tRow][tColumn]) {
				score[row][column] += sol(tRow,tColumn);
			}
		}
		return score[row][column];
	}	
}
