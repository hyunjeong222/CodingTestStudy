import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        // 중간원소
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n+1][n+1]; // 전체
        int[][] dp2 = new int[n+1][n+1]; // 중간 원소 포함 X
        dp2[1][1] = map[1][1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = map[i][j] + Math.max(dp[i-1][j], dp[i][j-1]);

                if (i == 1 && j == 1) continue;
                if (i == r && j == c) continue;
                if (dp2[i-1][j] != 0 || dp2[i][j-1] != 0) {
                    dp2[i][j] = map[i][j] + Math.max(dp2[i-1][j], dp2[i][j-1]);
                }
            }
        }

        int[][] dp3 = new int[n+1][n+1]; // 중간 원소 포함 O
        dp3[r][c] = dp[r][c];
        for (int i = r; i <= n; i++) {
            for (int j = c; j <= n; j++) {
                if (i == r && j == c) continue;
                dp3[i][j] = map[i][j] + Math.max(dp3[i-1][j], dp3[i][j-1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp3[n][n]).append(" ").append(dp2[n][n]).append("\n");

        System.out.println(sb.toString());
    }
}