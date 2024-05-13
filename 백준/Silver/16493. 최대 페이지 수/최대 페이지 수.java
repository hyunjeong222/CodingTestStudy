import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 남은 기간
        int m = Integer.parseInt(st.nextToken()); // 챕터 수

        int[] cost = new int[m+1];
        int[] value = new int[m+1];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken()); // 소요되는 일수
            value[i] = Integer.parseInt(st.nextToken()); // 페이지 수
        }

        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (cost[i] <= j) dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]]+value[i]);
                else dp[i][j] = dp[i-1][j];
            }
        }

        System.out.println(dp[m][n]);
    }
}