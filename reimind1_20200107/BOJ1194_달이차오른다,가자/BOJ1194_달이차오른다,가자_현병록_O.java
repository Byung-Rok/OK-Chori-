import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int sero, garo, memo[][][], minsik[], temp[], ny, nx, key;
	static String input;
	static char map[][];
	static final int atof = 6;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sero = Integer.parseInt(st.nextToken());
		garo = Integer.parseInt(st.nextToken());
		map = new char[sero][garo];
		memo = new int[sero][garo][1<<atof];
		for(int i=0; i<sero; i++) {
			input = br.readLine();
			for(int j=0; j<garo; j++) {
				map[i][j]=input.charAt(j);
				if(map[i][j]=='0') minsik = new int[] {i,j,0};
				Arrays.fill(memo[i][j], Integer.MAX_VALUE);
			}
		}
		System.out.println(escape());
	}
	static int[] dy= {-1,0,1,0}, dx= {0,1,0,-1};
	private static int escape() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(minsik);
		memo[minsik[0]][minsik[1]][minsik[2]]=0;
		while(!q.isEmpty()) {
			temp = q.poll();
			if(map[temp[0]][temp[1]]=='1') return memo[temp[0]][temp[1]][temp[2]];
			for(int d=0; d<4; d++) {
				ny = temp[0]+dy[d];
				nx = temp[1]+dx[d];
				key = temp[2];
				if(ny<0||nx<0||ny>=sero||nx>=garo||map[ny][nx]=='#') continue;
				if(map[ny][nx]>='a' && map[ny][nx]<='f') key|=(1<<(map[ny][nx]-'a'));
				if((map[ny][nx]>='A' && map[ny][nx]<='F') && (temp[2]&(1<<(map[ny][nx]-'A')))==0) continue;
				if(memo[ny][nx][key] <= memo[temp[0]][temp[1]][temp[2]]+1) continue;
				q.offer(new int[] {ny,nx,key});
				memo[ny][nx][key]=memo[temp[0]][temp[1]][temp[2]]+1;
			}
		}
		return -1;
	}
}
