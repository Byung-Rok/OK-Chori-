import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static char[][][] cube;
	/*
	 * cube - 
	 * 0-윗면
	 * 1-앞면
	 * 2-아랫면
	 * 3-뒷면
	 * 4-왼쪽
	 * 5-오른쪽
	 */
	private static void top() {
		thisSideRatate(0);
		//앞면저장
		char temp[] = cube[1][0];
		//오른쪽면 -> 앞면
		cube[1][0] = cube[5][0];
		//뒷면->오른쪽면
		cube[5][0] = cube[3][0];
		//왼쪽면 -> 뒷면
		cube[3][0] = cube[4][0];
		//앞면 -> 왼쪽면
		cube[4][0] = temp;
	}
	private static void bottom() {
		thisSideRatate(2);
		//뒷면저장
		char temp[] = cube[3][2];
		//오른쪽면 -> 뒷면
		cube[3][2] = cube[5][2];
		//앞면->오른쪽면
		cube[5][2] = cube[1][2];
		//왼쪽면 -> 앞면
		cube[1][2] = cube[4][2];
		//뒷면 -> 왼쪽면
		cube[4][2] = temp;
	}
	private static void front() {
		thisSideRatate(1);
		//윗면저장
		char temp[] = new char[3];
		temp[0] = cube[0][2][0];
		temp[1] = cube[0][2][1];
		temp[2] = cube[0][2][2];
		//왼쪽면 -> 윗면
		cube[0][2][2] = cube[4][0][2];
		cube[0][2][1] = cube[4][1][2];
		cube[0][2][0] = cube[4][2][2];
		//아래쪽->왼쪽면
		cube[4][0][2] = cube[2][2][2];
		cube[4][1][2] = cube[2][2][1];
		cube[4][2][2] = cube[2][2][0];
		//오른쪽면 -> 아래쪽면
		cube[2][2][2] = cube[5][2][0];
		cube[2][2][1] = cube[5][1][0];
		cube[2][2][0] = cube[5][0][0];
		//윗면 -> 오른쪽면
		cube[5][0][0] = temp[0];
		cube[5][1][0] = temp[1];
		cube[5][2][0] = temp[2];
	}
	private static void back() {
		thisSideRatate(3);
		//윗면저장
		char temp[] = new char[3];
		temp[0] = cube[0][0][0];
		temp[1] = cube[0][0][1];
		temp[2] = cube[0][0][2];
		//오른쪽면 -> 윗면
		cube[0][0][2] = cube[5][2][2];
		cube[0][0][1] = cube[5][1][2];
		cube[0][0][0] = cube[5][0][2];
		//아래쪽->오른쪽면
		cube[5][0][2] = cube[2][0][0];
		cube[5][1][2] = cube[2][0][1];
		cube[5][2][2] = cube[2][0][2];
		//왼쪽면 -> 아래쪽면
		cube[2][0][0] = cube[4][2][0];
		cube[2][0][1] = cube[4][1][0];
		cube[2][0][2] = cube[4][0][0];
		//윗면 -> 왼쪽면
		cube[4][2][0] = temp[0];
		cube[4][1][0] = temp[1];
		cube[4][0][0] = temp[2];
	}
	private static void left() {
		thisSideRatate(4);
		//윗면저장
		char temp[] = new char[3];
		temp[0] = cube[0][0][0];
		temp[1] = cube[0][1][0];
		temp[2] = cube[0][2][0];
		//뒷면 -> 윗면
		cube[0][0][0] = cube[3][2][2];
		cube[0][1][0] = cube[3][1][2];
		cube[0][2][0] = cube[3][0][2];
		//아래쪽->뒷면
		cube[3][0][2] = cube[2][0][2];
		cube[3][1][2] = cube[2][1][2];
		cube[3][2][2] = cube[2][2][2];
		//앞면 -> 아래쪽면
		cube[2][0][2] = cube[1][2][0];
		cube[2][1][2] = cube[1][1][0];
		cube[2][2][2] = cube[1][0][0];
		//윗면 -> 앞면
		cube[1][0][0] = temp[0];
		cube[1][1][0] = temp[1];
		cube[1][2][0] = temp[2];
	}
	private static void right() {
		thisSideRatate(5);
		//윗면저장
		char temp[] = new char[3];
		temp[0] = cube[0][0][2];
		temp[1] = cube[0][1][2];
		temp[2] = cube[0][2][2];
		//앞면 -> 윗면
		cube[0][0][2] = cube[1][0][2];
		cube[0][1][2] = cube[1][1][2];
		cube[0][2][2] = cube[1][2][2];
		//아래쪽->앞면
		cube[1][0][2] = cube[2][2][0];
		cube[1][1][2] = cube[2][1][0];
		cube[1][2][2] = cube[2][0][0];
		//뒷면 -> 아래쪽면
		cube[2][2][0] = cube[3][2][0];
		cube[2][1][0] = cube[3][1][0];
		cube[2][0][0] = cube[3][0][0];
		//윗면 -> 뒷면
		cube[3][0][0] = temp[2];
		cube[3][1][0] = temp[1];
		cube[3][2][0] = temp[0];
	}
	/**
	 * 돌리는 면이 돌리는 함수
	 * @param side - 돌릴려는 면
	 */
	private static void thisSideRatate(int side) {
		char temp = cube[side][0][0];
		cube[side][0][0] = cube[side][2][0];
		cube[side][2][0] = cube[side][2][2];
		cube[side][2][2] = cube[side][0][2];
		cube[side][0][2] = temp;
		temp = cube[side][0][1];
		cube[side][0][1] = cube[side][1][0];
		cube[side][1][0] = cube[side][2][1];
		cube[side][2][1] = cube[side][1][2];
		cube[side][1][2] = temp;
	}
	static int testcase, cycle;
	static String command;
	static StringBuilder sb;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		testcase = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc=1; tc<=testcase; tc++) {
			cube = new char[][][] {
				{
					{'w', 'w', 'w'},
					{'w', 'w', 'w'},
					{'w', 'w', 'w'}
				},
				{
					{'r', 'r', 'r'},
					{'r', 'r', 'r'},
					{'r', 'r', 'r'}
				},
				{
					{'y', 'y', 'y'},
					{'y', 'y', 'y'},
					{'y', 'y', 'y'}
				},
				{
					{'o', 'o', 'o'},
					{'o', 'o', 'o'},
					{'o', 'o', 'o'}
				},
				{
					{'g', 'g', 'g'},
					{'g', 'g', 'g'},
					{'g', 'g', 'g'}
				},
				{
					{'b', 'b', 'b'},
					{'b', 'b', 'b'},
					{'b', 'b', 'b'}
				}
			};
			cycle = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<cycle; i++) {
				command = st.nextToken();
				char com = command.charAt(0);
				int repeat = command.charAt(1)=='-' ? 3 : 1;
				for(int r=0; r<repeat; r++) {
					if(com=='U') {
						top();
					}else if(com=='D') {
						bottom();
					}else if(com=='F') {
						front();
					}else if(com=='B') {
						back();
					}else if(com=='L') {
						left();
					}else if(com=='R') {
						right();
					}
				}
			}
			print();
		}
		System.out.println(sb.toString());
	}
	private static void print() {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				sb.append(cube[0][i][j]);
			}
			sb.append("\n");
		}
	}
	
}
