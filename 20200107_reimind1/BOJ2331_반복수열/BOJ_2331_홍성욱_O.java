import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static String A;
	static int P;
	static int [] count = new int[250000];
	static int res=0;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] input = br.readLine().split(" ");
		
		A = input[0];
		P = Integer.parseInt(input[1]);
		
		
		sol();
	}
	/*
	 * 9^5 + 9^5 + 9^5 + 9^5 = 236196
	 * 이므로 
	 * new int[250000]잡음..
	 */
	public static void sol() {
		
		while(true) {
			if(check()) {
				//count가 3개짜리가 생기면,,
				System.out.println(res);
				return;
			}
			count[Integer.parseInt(A)]++;
			A = makeNum(A);

		}
	}
	public static boolean check() {
		boolean flag = false;
		for(int i=0;i<count.length;i++) {
			if(count[i]==3) {
				flag = true;
			}
			if(count[i]==1) {
				res++;
			}
		}
		if(flag == false) {
			res=0;
		}
		return flag;
	}
	public static String makeNum(String num) {
		String res="";
		//A가 한자리숫자이면 0A로 만들기
		if(num.length()==1) {
			num = "0"+num;
		}
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0;i<num.length();i++) {
			list.add(Integer.parseInt(num.substring(i,i+1)));
		}
		
		int sum=0;
		for(int i=0;i<list.size();i++) {
			sum += Math.pow(list.get(i), P);
		}
		res = sum+"";

		return res;
	}
}
