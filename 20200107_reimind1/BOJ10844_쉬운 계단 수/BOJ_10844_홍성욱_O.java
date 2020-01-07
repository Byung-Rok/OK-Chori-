import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[][] dp = new int[101][10];
	//[1�� �ڸ�����~ 100�ڸ�][0~9����]
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i=1;i<10;i++) {
			dp[1][i]=1;
		}
		
		//i�� ���° �ڸ�?
		//j�� ����
		for(int i=2;i<=N;i++) {
			 // i��°�ڸ����� ����'0'�� 
            // i-1��°�ڸ��� ����'1'�� �˸� ��
			dp[i][0] = dp[i-1][1];
			
			for(int j=1;j<9;j++) {
				dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1])%1000000000;
			}
			
			// i��°�ڸ����� ����'9'��
            // i-1��°�ڸ����� ����'8'�� �˸� ��
			dp[i][9] = dp[i-1][8];
		}
		long res=0;
		for(int i=0;i<10;i++) {
			res+= dp[N][i];
		}
		System.out.println(res%1000000000);
		
	}

}
