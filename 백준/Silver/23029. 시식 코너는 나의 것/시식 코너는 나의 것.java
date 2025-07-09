import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] food = new int[100001];
        for (int i = 1; i <= n; i++) {
            food[i] = Integer.parseInt(br.readLine());
        }

        // dp[i][0] : 시식코너 i에서 첫 번째로 시식한 경우 - 이전 시식으로부터 쉬고 먹기
        // dp[i][1] : 시식코너 i에서 연속 두 번째로 시식한 경우 - 절반
        // dp[i][2] : 시식코너 i를 쉬는 경우
        long[][] dp = new long[100001][3];
        dp[1][1] = dp[1][2] = food[1];
        dp[2][0] = food[2];
        dp[2][1] = food[1]+(food[2]/2);
        dp[2][2] = food[1];

        long ans = Math.max(dp[2][0], dp[2][1]);

        for (int i = 3; i <= n; i++) {
            dp[i][0] = dp[i-1][2]+food[i];
            dp[i][1] = dp[i-1][0]+(food[i]/2);
            dp[i][2] = Math.max(dp[i-1][0], Math.max(dp[i-1][1],dp[i-1][2]));
        }

        ans = Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2]));

        System.out.println(ans);

        br.close();
    }
}