import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Stack;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Stack<Character> sf;
	static Stack<Character> sl;
	static String str;
	static int T;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc=0;tc<T;tc++) {
			str = br.readLine();
			
			sf = new Stack<>();
			sl = new Stack<>();
			
			int len = str.length();
			for(int a=0;a<len;a++) {
				char temp = str.charAt(a);
				if(temp=='<') {
					if(sf.isEmpty()) continue;
					sl.push(sf.pop());
				}else if(temp=='>') {
					if(sl.isEmpty()) continue;
					sf.push(sl.pop());
				}else if(temp=='-') {
					if(sf.isEmpty()) continue;
					sf.pop();
				}else {
					sf.push(temp);
				}
			}
			Iterator<Character> it = sf.iterator();
			while(it.hasNext()) {
				bw.append(it.next());
			}
			while(!sl.isEmpty()) {
				bw.append(sl.pop());
			}
			bw.append("\n");
		}
		bw.flush();
		bw.close();
	}
}
