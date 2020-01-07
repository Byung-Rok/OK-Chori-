import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int size;
	static long memo[][], result;
	static final int mod = 1000000000;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		memo = new long[size+1][10];
		for(int i=1; i<10; i++)
			memo[1][i]=1;
		for(int i=2; i<=size; i++) {
			memo[i][0]=memo[i-1][1]%mod;
			for(int j=1; j<9; j++) {
				memo[i][j] = (memo[i-1][j-1]+memo[i-1][j+1])%mod;
			}
			memo[i][9]=memo[i-1][8]%mod;
		}
		for(int i=0; i<10; i++) {
			result += memo[size][i];
		}
		System.out.println(result%mod);
	}
}
