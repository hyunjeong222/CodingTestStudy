import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, W1, W2;
    static int[] cost, value;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트케이스
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 1; i <= t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 후보자의 발표자 수
            // 각각 드론의 무게
            W1 = Integer.parseInt(st.nextToken());
            W2 = Integer.parseInt(st.nextToken());
            //
            cost = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[j] = Integer.parseInt(st.nextToken());
            }
            value = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                value[j] = Integer.parseInt(st.nextToken());
            }

            int max = getMax();

            sb.append("Problem ").append(i).append(": ").append(max).append("\n");
        }

        System.out.println(sb);
    }

    private static int getMax() {
        int[][] dp = new int[W1+1][W2+1];

        for (int i = 0; i < n; i++) {
            for (int w1 = W1; w1 >= 0; w1--) {
                for (int w2 = W2; w2 >= 0; w2--) {
                    if (w1 - cost[i] >= 0) {
                        dp[w1][w2] = Math.max(dp[w1][w2], dp[w1-cost[i]][w2] + value[i]);
                    }
                    if (w2 - cost[i] >= 0) {
                        dp[w1][w2] = Math.max(dp[w1][w2], dp[w1][w2-cost[i]] + value[i]);
                    }
                }
            }
        }

        return dp[W1][W2];
    }
}