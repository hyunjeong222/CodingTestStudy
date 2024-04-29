import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cost = new int[n+1];
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }
        int[] value = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            value[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n+1][100];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 100; j++){
                if (cost[i] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]]+value[i]);
                } else dp[i][j] = dp[i-1][j];
            }
        }

        System.out.println(dp[n][99]);
    }
}