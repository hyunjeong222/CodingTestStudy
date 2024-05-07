import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static public final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 커피의 개수
        int k = Integer.parseInt(st.nextToken()); // 섭취해야 하는 카페인 양

        int[] dp = new int[k+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n+1; i++) {
            int caffeine = Integer.parseInt(st.nextToken());
            for (int j = k; j >= caffeine; j--) {
                dp[j] = Math.min(dp[j], dp[j-caffeine]+1);
            }
        }

        System.out.println(dp[k] == INF ? -1 : dp[k]);

    }
}