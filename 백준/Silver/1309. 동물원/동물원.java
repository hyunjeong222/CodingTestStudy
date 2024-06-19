import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int MOD = 9901;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // i번째 줄에서, j번째 칸에 동물을 놓을 수 있는 경우의 수는 n
        // j = 0 : 아무 곳에도 동물을 놓지 않는 경우, 1 : 첫번째 칸, 2 : 두번째 칸에 동물을 놓음
        long[][] dp = new long[n+1][3];
        dp[1][0] = dp[1][1] = dp[1][2] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i-1][0]+dp[i-1][1]+dp[i-1][2]) % MOD;
            dp[i][1] = (dp[i-1][0]+dp[i-1][2]) % MOD;
            dp[i][2] = (dp[i-1][0]+dp[i-1][1]) % MOD;
        }
        long ans = (dp[n][0]+dp[n][1]+dp[n][2]) % MOD;
        System.out.println(ans);
        br.close();
    }
}