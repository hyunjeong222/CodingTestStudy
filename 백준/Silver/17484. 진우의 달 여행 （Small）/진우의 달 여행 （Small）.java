import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int[][][] dp = new int[n][m][3]; // d 방향으로부터 온 점 (n, m)
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i][j], INF);
            }
        }

        for (int j = 0; j < m; j++) {
            dp[0][j][0] = map[0][j];
            dp[0][j][1] = map[0][j];
            dp[0][j][2] = map[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    dp[i][j][0] = Math.min(dp[i-1][j+1][1], dp[i-1][j+1][2]) + map[i][j];
                    dp[i][j][1] = dp[i-1][j][0] + map[i][j];
                } else if (j == m-1) {
                    dp[i][j][2] = Math.min(dp[i-1][j-1][1], dp[i-1][j-1][0]) + map[i][j];
                    dp[i][j][1] = dp[i-1][j][2] + map[i][j];
                } else {
                    dp[i][j][0] = Math.min(dp[i-1][j+1][1], dp[i-1][j+1][2]) + map[i][j];
                    dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2]) + map[i][j];
                    dp[i][j][2] = Math.min(dp[i-1][j-1][1], dp[i-1][j-1][0]) + map[i][j];
                }
            }
        }

        int min = INF;
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < 3; i++) {
                min = Math.min(min, dp[n-1][j][i]);
            }
        }

        System.out.println(min);

        br.close();
    }
}