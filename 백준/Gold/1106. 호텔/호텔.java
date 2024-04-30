import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken()); // 적어도 늘릴려는 호텔의 고객수
        int n = Integer.parseInt(st.nextToken()); // 도시의 수

        int[] dp = new int[c+101];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            for (int j = value; j < c+101; j++) {
                dp[j] = Math.min(dp[j], dp[j-value]+cost);
            }
        }

        int ans = INF;
        for (int i = c; i < c+101; i++) {
            ans = Math.min(ans, dp[i]);
        }
        System.out.println(ans);
    }
}