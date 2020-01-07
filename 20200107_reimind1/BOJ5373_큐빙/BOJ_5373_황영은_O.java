import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[][] up = {
			{1, 0, 2}, {1, 0, 1}, {1, 0, 0},
			{3, 0, 2}, {3, 0, 1}, {3, 0, 0},
			{2, 0, 2}, {2, 0, 1}, {2, 0, 0},
			{4, 0, 2}, {4, 0, 1}, {4, 0, 0}
	};
	static int[][] down = {
			{4, 2, 0}, {4, 2, 1}, {4, 2, 2},
			{2, 2, 0}, {2, 2, 1}, {2, 2, 2},
			{3, 2, 0}, {3, 2, 1}, {3, 2, 2},
			{1, 2, 0}, {1, 2, 1}, {1, 2, 2}
	};
	static int[][] front = {
			{0, 2, 0}, {0, 2, 1}, {0, 2, 2},
			{4, 0, 0}, {4, 1, 0}, {4, 2, 0},
			{5, 0, 0}, {5, 1, 0}, {5, 2, 0},
			{3, 2, 2}, {3, 1, 2}, {3, 0, 2}
	};
	static int[][] back = {
			{0, 0, 2}, {0, 0, 1}, {0, 0, 0},
			{3, 0, 0}, {3, 1, 0}, {3, 2, 0},
			{5, 2, 2}, {5, 1, 2}, {5, 0, 2},
			{4, 2, 2}, {4, 1, 2}, {4, 0, 2}
	};	
	static int[][] left = {
			{0, 0, 0}, {0, 1, 0}, {0, 2, 0},
			{1, 0, 0}, {1, 1, 0}, {1, 2, 0},
			{5, 2, 0}, {5, 2, 1}, {5, 2, 2},
			{2, 2, 2}, {2, 1, 2}, {2, 0, 2}
	};
	static int[][] right = {
			{0, 2, 2}, {0, 1, 2}, {0, 0, 2},
			{2, 0, 0}, {2, 1, 0}, {2, 2, 0},
			{5, 0, 2}, {5, 0, 1}, {5, 0, 0},
			{1, 2, 2}, {1, 1, 2}, {1, 0, 2}
	};
	static int[][] side = {
			{0, 0}, {0, 1}, {0, 2}, {1, 2}, {2, 2}, {2, 1}, {2, 0}, {1, 0}
	};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			char[][][] cube = {
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
					},
					{
						{'y', 'y', 'y'},
						{'y', 'y', 'y'},
						{'y', 'y', 'y'}
					}
			};
			
			int N = Integer.parseInt(br.readLine());
			String[] input = br.readLine().split(" ");
			for(int i = 0; i < N; i++) {
				char command = input[i].charAt(0);
				int dir = input[i].charAt(1) == '+' ? 1 : -1;
				
				switch(command) {
				case 'U':
					rotate(0, up, dir, cube);
					break;
				case 'D':
					rotate(5, down, dir, cube);
					break;
				case 'L':
					rotate(3, left, dir, cube);
					break;
				case 'R':
					rotate(4, right, dir, cube);
					break;
				case 'F':
					rotate(1, front, dir, cube);
					break;
				case 'B':
					rotate(2, back, dir, cube);
					break;
				}
			}
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					System.out.print(cube[0][i][j]);
				} System.out.println();
			}
		}
	}
	private static void rotate(int idx, int[][] arr, int dir, char[][][] cube) {
		if(dir == 1) {
			char t0 = cube[idx][side[7][0]][side[7][1]];
			char t1 = cube[idx][side[6][0]][side[6][1]];
			for(int i = 7; i >= 2; i-=2) {
				cube[idx][side[i][0]][side[i][1]] = cube[idx][side[i - 2][0]][side[i - 2][1]];
				cube[idx][side[i - 1][0]][side[i - 1][1]] = cube[idx][side[i - 3][0]][side[i - 3][1]];
			}
			cube[idx][side[0][0]][side[0][1]] = t1;
			cube[idx][side[1][0]][side[1][1]] = t0;
			
			t0 = cube[arr[9][0]][arr[9][1]][arr[9][2]];
			t1 = cube[arr[10][0]][arr[10][1]][arr[10][2]];
			char t2 = cube[arr[11][0]][arr[11][1]][arr[11][2]];
			
			for(int i = 11; i >=3; i -=3) {
				cube[arr[i][0]][arr[i][1]][arr[i][2]] = cube[arr[i - 3][0]][arr[i - 3][1]][arr[i - 3][2]];
				cube[arr[i - 1][0]][arr[i - 1][1]][arr[i - 1][2]] = cube[arr[i - 4][0]][arr[i - 4][1]][arr[i - 4][2]];
				cube[arr[i - 2][0]][arr[i - 2][1]][arr[i - 2][2]] = cube[arr[i - 5][0]][arr[i - 5][1]][arr[i - 5][2]];
			}

			cube[arr[0][0]][arr[0][1]][arr[0][2]] = t0;
			cube[arr[1][0]][arr[1][1]][arr[1][2]] = t1;
			cube[arr[2][0]][arr[2][1]][arr[2][2]] = t2;
		} else {
			char t0 = cube[idx][side[0][0]][side[0][1]];
			char t1 = cube[idx][side[1][0]][side[1][1]];
			for(int i = 0; i < 6; i+=2) {
				cube[idx][side[i][0]][side[i][1]] = cube[idx][side[i + 2][0]][side[i + 2][1]];
				cube[idx][side[i + 1][0]][side[i + 1][1]] = cube[idx][side[i + 3][0]][side[i + 3][1]];
			}
			cube[idx][side[6][0]][side[6][1]] = t0;
			cube[idx][side[7][0]][side[7][1]] = t1;
			
			t0 = cube[arr[0][0]][arr[0][1]][arr[0][2]];
			t1 = cube[arr[1][0]][arr[1][1]][arr[1][2]];
			char t2 = cube[arr[2][0]][arr[2][1]][arr[2][2]];
			for(int i = 0; i < 9; i+=3) {
				cube[arr[i][0]][arr[i][1]][arr[i][2]] = cube[arr[i + 3][0]][arr[i + 3][1]][arr[i + 3][2]];
				cube[arr[i + 1][0]][arr[i + 1][1]][arr[i + 1][2]] = cube[arr[i + 4][0]][arr[i + 4][1]][arr[i + 4][2]];
				cube[arr[i + 2][0]][arr[i + 2][1]][arr[i + 2][2]] = cube[arr[i + 5][0]][arr[i + 5][1]][arr[i + 5][2]];
			}
			cube[arr[9][0]][arr[9][1]][arr[9][2]] = t0;
			cube[arr[10][0]][arr[10][1]][arr[10][2]] = t1;
			cube[arr[11][0]][arr[11][1]][arr[11][2]] = t2;
		}
	}

}
