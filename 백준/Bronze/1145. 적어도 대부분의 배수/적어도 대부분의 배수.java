import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
   public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();

       StringTokenizer st = new StringTokenizer(br.readLine());
       int[] arr = new int[5];
       for (int i = 0; i < 5; i++) {
           arr[i] = Integer.parseInt(st.nextToken());
       }

       int n = 0;
       int cnt = 0;
       while (true) {
           n++;
           for (int i = 0; i < 5; i++) {
               if (n >= arr[i] && n % arr[i] == 0) cnt++;
           }

           if (cnt > 2) break;
           cnt = 0;
       }

       System.out.println(n);

       br.close();
   }
}