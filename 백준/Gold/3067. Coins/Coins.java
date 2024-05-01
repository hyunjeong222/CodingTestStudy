import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            int n = Integer.parseInt(br.readLine());
            int[] coins = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                coins[i] = Integer.parseInt(st.nextToken()); // 동전의 각 금액
            }
            int m = Integer.parseInt(br.readLine());
            int[] dp = new int[m+1];
            dp[0] = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= m; j++) {
                    if (j-coins[i] >= 0) {
                        dp[j] += dp[j-coins[i]];
                    }
                }
            }
            sb.append(dp[m]).append("\n");
        }
        System.out.println(sb);
    }
}