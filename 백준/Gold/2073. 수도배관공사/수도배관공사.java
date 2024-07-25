import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        int[] cost = new int[p];
        int[] value = new int[p];
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());;
        }

        int[] dp = new int[d+1];
        dp[0] = Integer.MAX_VALUE;
        for (int i = 0; i < p; i++) {
            for (int j = d; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], Math.min(dp[j-cost[i]], value[i]));
            }
        }

        System.out.println(dp[d]);

    }
}