import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final long MOD = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 길이

        // 길이가 i, j로 끝나는 수
        long[][] dp = new long[n+1][10];
        // 초기화
        // 길이가 1인 계단수는 9
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) { // 시작하는 수
                if (j == 0) dp[i][j] = dp[i-1][j+1] % MOD; // 시작 수가 0일때 올 수 있는 수 1
                else if (j == 9) dp[i][j] = dp[i-1][j-1] % MOD; // 시작 수가 9일때 올 수 있는 수 8
                else dp[i][j] = (dp[i-1][j-1]+dp[i-1][j+1]) % MOD;
            }
        }

        long sum = 0;
        for (int i = 0; i <= 9; i++) {
            sum += dp[n][i];
        }

        System.out.println(sum%MOD);

        br.close();
    }
}