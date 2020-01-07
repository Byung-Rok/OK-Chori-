import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;


public class Main {
	static String input;
	static int testcase;
	static char[] cArr;
	static Stack<Character> main, buff;
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		main = new Stack<>();
		buff = new Stack<>();
		testcase = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=testcase; tc++) {
			main.clear();
			buff.clear();
			input = br.readLine();
			cArr = input.toCharArray();
			for(char c : cArr) {
				if(c=='<') {
					if(!main.isEmpty()) buff.push(main.pop());
				}else if(c=='>') {
					if(!buff.isEmpty()) main.push(buff.pop());
				}else if(c=='-') {
					if(!main.isEmpty()) {
						main.pop();
					}
				}else {
					main.push(c);
				}
			}
			while(!buff.isEmpty()) main.push(buff.pop());
			for(char c : main) {
				bw.append(c);
			}
			bw.newLine();
		}
		bw.flush();
	}
}
