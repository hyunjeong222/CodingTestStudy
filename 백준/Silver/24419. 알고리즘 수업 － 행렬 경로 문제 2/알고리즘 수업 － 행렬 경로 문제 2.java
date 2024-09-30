import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] map = new int[n+1][n+1];
        StringTokenizer st;
        for (int i = 1 ; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long ans1 = matrix_path();
        int ans2 = n*n;

        StringBuilder sb = new StringBuilder();
        sb.append(ans1 % MOD).append(" ").append(ans2);
        System.out.println(sb.toString());
    }

    private static long matrix_path() {
        long[][] dp = new long[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            dp[0][i] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MOD;
            }
        }
        return dp[n][n];
    }
}