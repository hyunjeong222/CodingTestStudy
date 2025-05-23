import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 수열 A의 크기
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];

        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = A[i];
            for (int j = i-1; j >= 0; j--) {
                if (A[i] > A[j]) {
                    dp[i] = Math.max(dp[i], A[i]+dp[j]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);

        br.close();
    }
}