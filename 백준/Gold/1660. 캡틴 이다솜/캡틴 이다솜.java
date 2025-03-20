import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 길이
        int[] dp = new int[125]; // 사이즈당 더미에 필요한 대포알의 개수
        dp[0] = 0; dp[1] = 1;
        int idx = 1;
        while (dp[idx] <= n) {
            idx++;
            dp[idx] = dp[idx-1] + (dp[idx-1]-dp[idx-2]) + idx;
        }

        // 대포알 개수당 (N) 만들 수 있는 최소한의 더미 개수
        int[] cnt = new int[n+1];
        Arrays.fill(cnt, Integer.MAX_VALUE);
        cnt[0] = 0;
        cnt[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < idx; j++) {
                if (dp[j] > i) break;
                cnt[i] = Math.min(cnt[i], cnt[i-dp[j]]+1);
            }
        }

        System.out.println(cnt[n]);
    }
}