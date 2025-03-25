import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 앱의 개수
        int k = Integer.parseInt(st.nextToken()); // 필요한 메모리

        int[] m = new int[n]; // 앱의 각 바이트
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }

        int[] c = new int[n]; // 사용되는 비용
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j] : i번째 앱까지에 대하여 비용 j로 확보 가능한 최대 메모리
        int[][] dp = new int[n][10001];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int cost = c[i];
            int memory = m[i];
            for (int j = 0; j < 10001; j++) {
                if (i == 0) {
                    if (j >= cost) dp[i][j] = memory;
                } else {
                    if (j >= cost) {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost]+memory);
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }

                if (dp[i][j] >= k) {
                    ans = Math.min(ans, j);
                }
            }
        }

        System.out.println(ans);

        br.close();
    }
}