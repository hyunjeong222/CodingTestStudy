import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MOD = 10007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 학생수
        int m = Integer.parseInt(st.nextToken()); // 최대 가지고 있는 블럭수
        int h = Integer.parseInt(st.nextToken()); // 쌓아야하는 높이

        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
             st = new StringTokenizer(br.readLine());
             if (st.hasMoreTokens()) {
                 int j = 0;
                 while (st.hasMoreTokens()) {
                     board[i][j++] = Integer.parseInt(st.nextToken());
                 }
             }
        }

        int[][] dp = new int[n+1][h+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= h; j++) {
                dp[i][j] = dp[i-1][j];
                for (int k = 1; k <= m; k++) {
                    int block = board[i-1][k-1];
                    if (block == 0) continue;
                    if (j - block >= 0) {
                        dp[i][j] += dp[i-1][j-block];
                        dp[i][j] %= MOD;
                    }
                }
            }
        }

        System.out.println(dp[n][h]);
    }
}