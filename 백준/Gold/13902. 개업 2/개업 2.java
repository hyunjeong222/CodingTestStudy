import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 10001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 주문 수
        int m = Integer.parseInt(st.nextToken()); // 웍 수

        int[] size = new int[INF];
        st = new StringTokenizer(br.readLine());
        // 같은 크기의 웍을 여러 개 가지고 있을 수 있음
        for (int i = 0; i < m; i++) {
            size[Integer.parseInt(st.nextToken())]++;
        }

        int[] dp = new int[n+1]; // i개의 짜장면을 만들기 위한 최소 요리 횟수
        Arrays.fill(dp, INF);
        dp[0] = 0;  // 0개의 짜장면을 만드는데 0번 필요
        for (int i = 1; i <= n; i++) { // 짜장면 주문 수
            // 단일 웍
            if (size[i] > 0) dp[i] = 1;
            for (int j = 1; j <= i/2; j++) { // 웍 사이즈
                // 두 개의 웍 사이즈가 같고, 웍이 두 개 이상 존재 ?
                if (j == i-j && size[j] >= 2) dp[i] = 1;
                    // 두 개의 웍 사이즈가 다르고, 웍이 한 개 이상 존재 ?
                else if (j != i-j && size[j] > 0 && size[i-j] > 0) dp[i] = 1;
                    // 한 번에 조리 불가능
                else if (dp[j] != INF && dp[i-j]!= INF) dp[i] = Math.min(dp[i], dp[j]+dp[i-j]);
            }
        }

        System.out.println(dp[n] == INF ? -1 : dp[n]);

        br.close();
    }
}