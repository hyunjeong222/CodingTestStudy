import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MOD = 1_234_567;
        // dp[i][j] : 길이가 i면서 마지막 숫자가 j
        int[][] dp = new int[1001][10];
        Arrays.fill(dp[1], 1);

        for (int i = 2; i < 1001; i++) {
            dp[i][0] = dp[i-1][7] % MOD;
            dp[i][1] = (dp[i-1][2]+dp[i-1][4]) % MOD;
            dp[i][2] = (dp[i-1][1]+dp[i-1][3]+dp[i-1][5]) % MOD;
            dp[i][3] = (dp[i-1][2]+dp[i-1][6]) % MOD;
            dp[i][4] = (dp[i-1][1]+dp[i-1][5]+dp[i-1][7]) % MOD;
            dp[i][5] = (dp[i-1][2]+dp[i-1][4]+dp[i-1][6]+dp[i-1][8]) % MOD;
            dp[i][6] = (dp[i-1][3]+dp[i-1][5]+dp[i-1][9]) % MOD;
            dp[i][7] = (dp[i-1][4]+dp[i-1][8]+dp[i-1][0]) % MOD;
            dp[i][8] = (dp[i-1][5]+dp[i-1][7]+dp[i-1][9]) % MOD;
            dp[i][9] = (dp[i-1][6]+dp[i-1][8]) % MOD;
        }

        int t = Integer.parseInt(br.readLine());
        int n;
        StringBuilder sb = new StringBuilder();
        while (t-->0) {
            n = Integer.parseInt(br.readLine());

            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum = (sum + dp[n][i])%MOD;
            }

            sb.append(sum).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}