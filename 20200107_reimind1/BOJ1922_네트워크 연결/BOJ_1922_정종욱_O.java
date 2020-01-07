import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M;
    static int[] root;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
 
        Edge[] edge = new Edge[M];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edge[i] = new Edge(f, r, c);
        }
        Arrays.sort(edge);
        root = new int[N + 1];
        for (int i=1; i<=N; i++) {
        	root[i] = i;
        }
        int sum = 0;
        for (int i=0; i<M; i++) {
            Edge e = edge[i];
            int f = e.f;
            int r = e.r;
            int x = find(f);
            int y = find(r);
            if (x != y) {
                root[x] = y;
                sum += e.c;
            }
        }
        System.out.println(sum);
    }
 
    public static int find(int x) {
        if (root[x] == x) return x;
        else return root[x] = find(root[x]);
    }
 
    public static class Edge implements Comparable<Edge> {
        int f, r, c;
 
        public Edge(int f, int r, int c) {
            this.f = f;
            this.r = r;
            this.c = c;
        }
        @Override
        public int compareTo(Edge o) {
            return this.c - o.c;
        }
    }
}
