import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = 1000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()) + 1000_000;
        int[] dp = new int[2_000_001];
        dp[1000_001] = 1;
        if (n < 1000_000) {
            for (int i = 999_999; i >= n; i--) {
                dp[i] = (dp[i+2]-dp[i+1]) % INF;
            }
        } else {
            for (int i = 1000_002; i <= n; i++) {
                dp[i] = (dp[i-2]+dp[i-1]) % INF;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (dp[n] < 0)  sb.append(-1).append("\n").append(Math.abs(dp[n]));
        else if (dp[n] == 0) sb.append(0).append("\n").append(Math.abs(dp[n]));
        else sb.append(1).append("\n").append(Math.abs(dp[n]));

        System.out.println(sb.toString());

        br.close();
    }
}