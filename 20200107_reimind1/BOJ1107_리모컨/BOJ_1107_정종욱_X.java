// 테케 다맞으나 안됨

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,num,min,temp,cnt,slen;
	static String str;
	static boolean yn[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		num = Integer.parseInt(br.readLine());
		
		yn = new boolean[10]; // yes no
		min = Math.abs(N-100);
		
		st = new StringTokenizer(br.readLine());
		for(int a=0;a<num;a++) {
			yn[Integer.parseInt(st.nextToken())] = true;
		}
		
		str = Integer.toString(N);
		slen = str.length(); // 문자열길이
		makeNum(0);
		System.out.println(min);
	}

	private static void makeNum(int idx) {
		if(idx==slen) {
			cnt=0;
			String ss = Integer.toString(temp);
			cnt+=ss.length();
			cnt+=Math.abs(temp-N);
			if(cnt<min) min=cnt;
			
			cnt=0;
			temp+=Math.pow(10, idx);
			ss = Integer.toString(temp);
			cnt+=ss.length();
			cnt+=Math.abs(temp-N);
			if(cnt<min) min=cnt;
			temp-=Math.pow(10, idx);
			return;
		}
		
		char c = str.charAt(idx);
		int cc = c-'0';
		
		if(idx==0 && slen>1) {
			makeNum(idx+1);
		}
		if(yn[cc]==false) { // 가능한숫자
			temp+=(cc)*Math.pow(10,slen-idx-1);
			makeNum(idx+1);
		}else {	// 불가능숫자
			for(int a=0;a<10;a++) {
				if(yn[a]==true)continue;
				else {
					temp+=a*Math.pow(10,slen-idx-1);
					makeNum(idx+1);
					temp-=a*Math.pow(10, slen-idx-1);
				}
			}
		}
	}

}
