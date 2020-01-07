import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 

public class Main {
     
    static boolean map[][];
    static boolean visit[][][];
    static int K, W, H;
    static int dxH[] = {+1, +2, +2, +1, -1, -2, -2, -1};
    static int dyH[] = {-2, -1, +1, +2, +2, +1, -1, -2};
    static int dx[] = { 0,  0, -1, +1};
    static int dy[] = {-1, +1,  0,  0};
     
    static int bfs(){
         
        Queue<Entry> queue = new LinkedList<Entry>();
        Entry now = new Entry(0,0,0,0);
        queue.offer(now);
        visit[0][0][0]=true;
         
         
        while(!queue.isEmpty()){
            now = queue.poll();
            int x = now.x;
            int y = now.y;
            int cntHorse = now.cntHorse;
             
            if(x==H-1 && y==W-1)
                return now.cnt;
             
            for(int i=0; i<4; i++){
                if(x+dx[i]>=0 && y+dy[i]>=0
                        && x+dx[i]<H && y+dy[i]<W
                        && !map[x+dx[i]][y+dy[i]]
                        && !visit[x+dx[i]][y+dy[i]][cntHorse]){
                        queue.offer(new Entry(x+dx[i], y+dy[i], now.cnt+1, cntHorse));
                        visit[x+dx[i]][y+dy[i]][cntHorse]=true;
                }
            }
             
            if(cntHorse==K)
                continue;
             
            for(int j=0; j<8; j++){
                if(x+dxH[j]>=0 && y+dyH[j]>=0
                        && x+dxH[j]<H && y+dyH[j]<W
                        && !visit[x+dxH[j]][y+dyH[j]][cntHorse+1]
                        && !map[x+dxH[j]][y+dyH[j]]){
                    queue.offer(new Entry(x+dxH[j],y+dyH[j],now.cnt+1,cntHorse+1));
                    visit[x+dxH[j]][y+dyH[j]][cntHorse+1]=true;
                }
            }
             
        }
         
        return -1;
    }
     
    public static void main(String[] args) {
     
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt(); 
        W = sc.nextInt(); 
        H = sc.nextInt(); 
        visit = new boolean[H][W][K+1];
        map = new boolean[H][W];
         
        for(int i=0; i<H; i++)
            for(int j=0; j<W; j++){
                if(sc.nextInt()==1)
                    map[i][j] = true;
            }
         
        System.out.println(bfs());
    }
 
}
 
class Entry{
    int x, y, cnt, cntHorse;
    Entry(int x, int y, int cnt, int cntHorse){
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.cntHorse = cntHorse;
    }
}
