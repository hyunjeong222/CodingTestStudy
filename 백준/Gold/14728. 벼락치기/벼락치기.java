import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 단원의 개수
        int t = Integer.parseInt(st.nextToken()); // 총 시간

        int[] cost = new int[n+1];
        int[] value = new int[n+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n+1][t+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= t; j++) {
                if (cost[i] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]]+value[i]);
                } else dp[i][j] = dp[i-1][j];
            }
        }

        System.out.println(dp[n][t]);
    }
}