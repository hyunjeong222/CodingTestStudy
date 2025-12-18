import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int cnt1 = 0;
        int cnt2 = 0;
        
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (x == 1) cnt1++;
            else cnt2++;
        }

        if (cnt1 >= cnt2 && (cnt1 - cnt2) % 3 == 0) System.out.println("Yes");
        else System.out.println("No");
    }
}