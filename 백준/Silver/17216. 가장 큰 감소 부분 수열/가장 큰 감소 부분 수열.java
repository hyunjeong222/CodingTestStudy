import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[n+1];
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            dp[i] = A[i];
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (A[i] < A[j]) dp[i] = Math.max(dp[i], dp[j]+A[i]);
            }
            
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);


        br.close();
    }
}