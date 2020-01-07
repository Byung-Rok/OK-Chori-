import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int goal, dp[], price[];
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		goal = Integer.parseInt(br.readLine());
		dp = new int[goal+1];
		price = new int[goal];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<goal; i++) price[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=goal; i++) {
			for(int j=1; j<=i; j++) {
				dp[i] = Math.max(dp[i], dp[i-j]+price[j-1]);
			}
		}
		System.out.println(dp[goal]);
	}
}
