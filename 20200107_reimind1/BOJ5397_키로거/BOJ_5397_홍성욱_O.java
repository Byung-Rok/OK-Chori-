import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;


public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int Test_Case = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=Test_Case; tc++) {
			String [] input = br.readLine().split("");
			
			Stack<String>[] list = new Stack[2];
			list[0] = new Stack<>(); list[1] = new Stack<>();
			
			
			
			for(int i=0;i<input.length;i++) {
				
				if(input[i].equals("<")) {
					if(!list[0].isEmpty()) {
						list[1].add(list[0].pop());
					}
				}else if(input[i].equals(">")) {
					if(!list[1].isEmpty()) {
						list[0].add(list[1].pop());
					}
				}else if(input[i].equals("-")) {
					if(!list[0].isEmpty()) {
						list[0].pop();
					}
				}else {
					list[0].push(input[i]);
				}
				
//				System.out.println(list.toString() + " :::"+cursor          +"         ���� : "+input[i]);
			}
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			while(!list[0].isEmpty()) {
				list[1].add(list[0].pop());
			}
			while(!list[1].isEmpty()) {
				bw.append(list[1].pop());
			}
			bw.flush();
			System.out.println();
		}
	}

}
