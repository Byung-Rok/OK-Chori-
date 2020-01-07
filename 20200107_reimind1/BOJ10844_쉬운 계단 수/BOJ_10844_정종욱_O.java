import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static long sum;
	static long DP[][] = new long[101][11];
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		for(int a=1;a<10;a++) {
			DP[1][a]=1;
		}
		for(int lv=2;lv<=N;lv++) {
			DP[lv][0]=DP[lv-1][1];
			for(int a=1;a<10;a++) {
				DP[lv][a] = (DP[lv-1][a-1]+DP[lv-1][a+1])%1000000000;
			}
		}
		for(int a=0;a<10;a++) {
			sum+=DP[N][a];
		}
		System.out.println(sum%1000000000);
	}
}
