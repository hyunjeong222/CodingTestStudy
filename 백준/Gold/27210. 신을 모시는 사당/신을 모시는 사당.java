import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int now = arr[i] == 1 ? -1 : 1;
            if (i == 0) {
                dp[i] = now;
            } else {
                dp[i] = Math.max(dp[i-1]+now, now);
            }
        }
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }

        dp = new int[n];
        for (int i = 0; i < n; i++) {
            int now = arr[i] == 1 ? 1 : -1;
            if (i == 0) {
                dp[i] = now;
            } else {
                dp[i] = Math.max(dp[i-1]+now, now);
            }
        }
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }
}