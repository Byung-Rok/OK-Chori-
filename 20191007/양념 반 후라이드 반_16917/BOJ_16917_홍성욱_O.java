package day20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16917_홍성욱_O {
	static int res=0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A=0;
		int B=0;
		int C=0;
		int X=0;
		int Y=0;
		
		String [] input = br.readLine().split(" ");
		A = Integer.parseInt(input[0]);
		B = Integer.parseInt(input[1]);
		C = Integer.parseInt(input[2]);
		X = Integer.parseInt(input[3]);
		Y = Integer.parseInt(input[4]);
		
		if(A+B < C*2) {
			// 양념, 후라이드로 사야함
			res = A*X + B*Y;

		}else {
			// 반반치킨으로 줄이기~~
			if(X>Y) {
				res += C*2*Y; 
				if(A*(X-Y) < C*2*(X-Y)) {
					res +=(A*(X-Y));
				}else {
					res += (C*2*(X-Y));
				}

			}else if(X<Y){
				res += C*2*X;
				if(B*(Y-X) < C*2*(Y-X)) {
					res+=(B*(Y-X));
				}else {
					res+=(C*2*(Y-X));
				}

			}else {
				res = C*2*(X+Y);

			}
		}
		System.out.println(res);
		
		
		
		
	}

}
