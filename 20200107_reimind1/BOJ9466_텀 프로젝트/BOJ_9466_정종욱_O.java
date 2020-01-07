import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int T,N,count,temp,start,std[];
    static boolean visited[],finished[];
    
    public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc=0;tc<T;tc++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			std = new int[N+1];
			visited = new boolean[N+1];
			finished = new boolean[N+1];
			count=0;
			
			for(int a=1;a<=N;a++) {
				std[a] = Integer.parseInt(st.nextToken());
			}
			
			for(int a=1;a<=N;a++) goDFS(a);
			bw.append(N-count+"\n");
		}
        bw.flush();
        bw.close();
	}

	private static void goDFS(int idx) {
		 if(visited[idx])
	            return;
	 
	        visited[idx] = true;
	        int next = std[idx];
	 
	        if(visited[next] != true)
	        	goDFS(next);
	        else {
	            if(finished[next] != true) {
	                count++;
	                for(int i=next;i!=idx;i=std[i])
	                    count++;
	            }
	        }
	        finished[idx] = true;
	}
}
