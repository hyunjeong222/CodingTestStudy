import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
   public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();

       // StringTokenizer st = new StringTokenizer(br.readLine());
       while(true) {
           int n = Integer.parseInt(br.readLine());

           // 종료 조건
           if (n == -1) break;

           int[] arr = new int[n];
           int sum = 0;
           int idx = 0;
           for (int i = 1; i < n; i++) {
               if (n%i == 0) {
                   arr[idx++] = i;
                   sum += i;
               }
           }

           if (sum != n) {
               sb.append(n).append(" is NOT perfect.").append("\n");
               continue;
           }

           sb.append(n).append(" = ");

           for (int i = 0; i < idx; i++) {
               if (i == idx-1) sb.append(arr[i]);
               else sb.append(arr[i]).append(" + ");
           }
           sb.append("\n");
       }

       System.out.println(sb.toString());

       br.close();
   }
}