import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
   static int goal, cycle, size, min, start, end;
   static boolean numbers[], isFirst;
   public static void main(String[] args) throws NumberFormatException, IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      goal = Integer.parseInt(br.readLine());
      cycle = Integer.parseInt(br.readLine());
      numbers = new boolean[10];
      if(cycle!=0) {
         StringTokenizer st = new StringTokenizer(br.readLine());
         while(st.hasMoreTokens()) numbers[Integer.parseInt(st.nextToken())]=true;
      }
      size = String.valueOf(goal).length();
      min = Math.abs(100-goal);
      if(!numbers[0]) min = Math.min(min, 1+goal);
      for(int i=(size-2)<=0 ? 0 : size-2; i<size+1; i++) {
         start = (int) Math.pow(10, i); end = (int) Math.pow(10, i+1);
         for(int j=start; j<end; j++) {
            if(check(j))
               min = Math.min(min, i+1+Math.abs(goal-j));
         }
      }
      System.out.println(min);
   }
   private static boolean check(int num) {
      while(num!=0) {
         if(numbers[num%10]) return false;
         num/=10;
      }
      return true;
   }
}