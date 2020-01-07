import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int size;
	static int buy[][], memo[][];
	static String input;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		buy = new int[size][size];
		memo = new int[size][1<<size];
		for(int i=0; i<size; i++) {
			input = br.readLine();
			Arrays.fill(memo[i], -1);
			for(int j=0; j<size; j++) {
				buy[i][j] = input.charAt(j)-'0';
			}
		}
		System.out.println(sell(0, 1, 0));
	}
	private static int sell(int now, int visited, int price) {
		if(memo[now][visited]!=-1) {
			return memo[now][visited];
		}
		boolean isLast = true;
		for(int i=0; i<size; i++) {
			if((visited&(1<<i))>0 || buy[now][i]<price) continue;
			memo[now][visited] = Math.max(memo[now][visited], sell(i, visited|(1<<i), buy[now][i]) + 1);
			isLast = false;
		}
		return isLast ? 1 : memo[now][visited];
	}
}
