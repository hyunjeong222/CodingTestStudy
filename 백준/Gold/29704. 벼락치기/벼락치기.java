import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static public final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 문제의 개수
        int t = Integer.parseInt(st.nextToken()); // 남은 제출 기한

        int[] cost = new int[n+1];
        int[] value = new int[n+1];
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken()); // 문제 푸는데 걸리는 일수
            int v = Integer.parseInt(st.nextToken()); // 해당 문제의 벌금
            value[i] = v;
            sum += v;
        }

        int[][] dp = new int[n+1][t+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= t; j++) {
                if (cost[i] <= j) dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]]+value[i]);
                else dp[i][j] = dp[i-1][j];
            }
        }

        System.out.println(sum-dp[n][t]);
    }
}