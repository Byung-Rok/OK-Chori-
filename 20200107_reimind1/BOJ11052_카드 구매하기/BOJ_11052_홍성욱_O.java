import java.util.*;

public class Main{
    static int n;
    static int[] p,d;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        p = new int[n+1];
        d = new int[n+1];
        for(int i=1;i<=n;i++){
            p[i] = sc.nextInt();
        }
        
        for(int i=1;i<=n; i++){
            for(int j=1; j<=i;j++){
                d[i] = Math.max(d[i-j]+p[j],d[i]);
            }
        }
        System.out.println(d[n]);
    }


}