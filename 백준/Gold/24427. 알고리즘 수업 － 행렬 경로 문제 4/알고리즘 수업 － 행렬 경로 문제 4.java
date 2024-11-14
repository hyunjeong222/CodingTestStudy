import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n+1][n+1];
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int p = Integer.parseInt(br.readLine());

        int[][] dp = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = map[i][j] + Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        // System.out.println(Arrays.deepToString(dp));

        int[][] dp2 = new int[n+1][n+1];
        boolean[][] checked = new boolean[n+1][n+1];
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dp2[r][c] = dp[r][c];
            checked[r][c] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (checked[i-1][j]) {
                    dp2[i][j] = Math.max(dp2[i][j], dp2[i-1][j]+map[i][j]);
                    checked[i][j] = true;
                }
                if (checked[i][j-1]) {
                    dp2[i][j] = Math.max(dp2[i][j], dp2[i][j-1]+map[i][j]);
                    checked[i][j] = true;
                }
            }
        }

        System.out.println(dp2[n][n]);
    }
}