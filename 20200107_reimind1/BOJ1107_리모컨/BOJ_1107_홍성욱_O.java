import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static int N;
	static int length;
	static boolean [] arr;
	static int M;
	static int res=Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		length = (N+"").length();
		M = Integer.parseInt(br.readLine());
		arr = new boolean[10];

		if(M>0) {
			String [] input = br.readLine().split(" ");
			for(int i=0;i<input.length ; i++) {
				arr[Integer.parseInt(input[i])]=true;
			}
		}


		int channel = 100;
		res = Math.abs(N-channel);

		if(res ==0) {
			System.out.println(res);
			return;
		}

		sol(0, "");

		System.out.println(res);
	}
	static void sol(int depth, String data) {
		if(depth>=length+1) {
			String temp="";
			int nn= 0;
			int count=0;
			int idx = length-1;
			if(length-1<=0) {
				idx=1;
			}
			for(int i=idx;i<=data.length();i++) {
				nn= Integer.parseInt(data.substring(0,i));
				count = (nn+"").length()+Math.abs(N-nn);
				if(res>count) {
					res = count;
				}
			}

			return;
		}else {
			for(int i=0;i<10; i++) {
				if(!arr[i]) {
					//버튼이 안망가졌으면
					sol(depth+1,data+i);
				}
			}
		}

	}

}