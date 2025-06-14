import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[][] dp = new long[100001][2];
        dp[1][1] = 1;
        dp[2][0] = 1; // 짝수
        dp[2][1] = 1; // 홀수
        dp[3][0] = 2;
        dp[3][1] = 2;
        int MOD = 1_000_000_009;
        for (int i = 4; i < 100001; i++) {
            dp[i][0] = (dp[i-1][1]+dp[i-2][1]+dp[i-3][1]) % MOD; // 짝수
            dp[i][1] = (dp[i-1][0]+dp[i-2][0]+dp[i-3][0]) % MOD; // 홀수
        }

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-->0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n][1]%MOD).append(" ").append(dp[n][0]).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}