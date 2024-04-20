import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int MOD = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[10001];
        dp[0] = 1; dp[2] = 1;
        for (int i = 4; i <= n; i+=2) {
            for (int j = 2; i-j >= 0; j+=2) {
                dp[i] = (dp[i] + (dp[j-2] * dp[i-j]) % MOD) % MOD;
            }
        }
        System.out.println(dp[n]);
    }
}