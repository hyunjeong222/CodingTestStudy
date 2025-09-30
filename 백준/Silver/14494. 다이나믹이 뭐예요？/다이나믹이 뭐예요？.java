import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        dp = new long[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(func(n, m)%MOD);

        br.close();
    }

    private static long func(int n, int m) {
        if (n == 1 && m == 1) return 1; // (1, 1) -> (1, 1) 경우의 수 1

        if (dp[n][m] != -1) return dp[n][m];
        long ans = (func(n ,m-1)+func(n-1, m)+func(n-1, m-1)) % MOD;
        dp[n][m] = ans;

        return ans;
    }
}