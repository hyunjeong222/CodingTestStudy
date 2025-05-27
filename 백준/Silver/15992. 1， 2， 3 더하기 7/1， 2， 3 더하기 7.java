import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_009;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        long[][] dp = new long[1001][1001];
        dp[1][1] = 1;
        dp[2][1] = 1; dp[2][2] = 1;
        dp[3][1] = 1; dp[3][2] = 2; dp[3][3] = 1;
        for (int i = 4; i <= 1000; i++) {
            for (int j = 2; j < i; j++) {
                dp[i][j] = (dp[i-3][j-1]+dp[i-2][j-1]+dp[i-1][j-1]) % MOD;
            }
            dp[i][i] = 1;
        }

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            sb.append(dp[n][m]%MOD).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}