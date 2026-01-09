import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken()); // 초기비용
        int y = Integer.parseInt(st.nextToken()); // 투자 기간

        long[] dp = new long[y+1];
        dp[0] = h;
        for (int i = 1; i <= y; i++) {
            dp[i] = (long)Math.floor(dp[i-1]*1.05);

            if (i >= 3) {
                dp[i] = Math.max(dp[i], (long)Math.floor(dp[i-3]*1.2));
            }

            if (i >= 5) {
                dp[i] = Math.max(dp[i], (long)Math.floor(dp[i-5]*1.35));
            }
        }

        System.out.println(dp[y]);

        br.close();
    }
}