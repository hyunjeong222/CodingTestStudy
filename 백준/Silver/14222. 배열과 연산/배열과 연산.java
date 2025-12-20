import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k;
    static Queue<Integer> que;
    static boolean[] checked;
   public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();

       StringTokenizer st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());
       k = Integer.parseInt(st.nextToken());

       que = new LinkedList<>();
       checked = new boolean[n+1];
       boolean flag = true;
       st = new StringTokenizer(br.readLine());
       for (int i = 1; i <= n; i++) {
           int num = Integer.parseInt(st.nextToken());
           if (num > n) {
               flag = false;
               break;
           }

           if (!checked[num]) checked[num] = true;
           else que.offer(num);
       }

       if (flag) {
           flag = numCheck();
       }

       if (flag) System.out.println(1);
       else System.out.println(0);

       br.close();
   }

    private static boolean numCheck() {
       while (!que.isEmpty()) {
           int now = que.poll();
           int next = now+k;

           if (next > n) return false;
           if (!checked[next]) checked[next] = true;
           else que.offer(next);
       }

       return true;
    }
}