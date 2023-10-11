import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int t, n, m;
    static int[] coins;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        while (t --> 0) {
            n = Integer.parseInt(br.readLine());
            coins = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            m = Integer.parseInt(br.readLine());

            dp = new int[m+1];
            dp[0] = 1; // 초깃값 설정
            for (int coin : coins) {
                for (int i = coin; i <= m; i++) {
                    dp[i] += dp[i - coin];
                }
            }
            bw.append(dp[m] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}