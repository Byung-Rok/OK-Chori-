import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int A,P,temp,sum,mul,idx;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException {
		st= new StringTokenizer(br.readLine());
		A= Integer.parseInt(st.nextToken());
		P= Integer.parseInt(st.nextToken());
		list = new ArrayList<>();

		list.add(A);
		cal();
		System.out.println(idx);
	}
	private static void cal() {

		while(A!=0) {
			mul=1;
			temp=A%10;
			A=A/10;
			for(int a=0;a<P;a++) {
			mul*=temp;
			}
			sum+=mul;
		} // while문 out
		A=sum;
		if(list.contains(sum)) {
			idx = list.indexOf(sum);
			return;
		}else
			list.add(sum);
		sum=0;
		cal();
	}

}
