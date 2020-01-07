import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int start, p, num, cnt;
	static int visited[] = new int[300001];
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		while(visited[start]==0) {
			num = start;
			cnt++;
			visited[start]=cnt;
			start=0;
			while(num!=0) {
				start += Math.pow(num%10, p);
				num/=10;
			}
		}
		System.out.println(visited[start]-1);
	}
}
