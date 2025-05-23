import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();
        int[] dp = new int[n];
        Arrays.fill(dp, INF);
        dp[0] = 0; // i번째 위치에 도달하기까지 소모한 최소 에너지
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] == 'O' && arr[j] == 'B' && dp[j] != INF) {
                    dp[i] = Math.min(dp[i], dp[j]+(i-j)*(i-j));
                } else if (arr[i] == 'J' && arr[j] == 'O' && dp[j] != INF) {
                    dp[i] = Math.min(dp[i], dp[j]+(i-j)*(i-j));
                } else if (arr[i] == 'B' && arr[j] == 'J' && dp[j] != INF) {
                    dp[i] = Math.min(dp[i], dp[j] + (i-j)*(i-j));
                }
            }
        }

        System.out.println(dp[n-1] == INF ? -1 : dp[n-1]);

        br.close();
    }
}