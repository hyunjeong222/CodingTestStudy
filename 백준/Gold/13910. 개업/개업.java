import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = 10001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 짜장면의 수
        int m = Integer.parseInt(st.nextToken()); // 웍의 개수

        int[] wok = new int[INF];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            // 같은 크기의 웍을 여러 개 가지고 있을 수 있음
            wok[Integer.parseInt(st.nextToken())]++;
        }

        int[] dp = new int[n+1]; // i개의 짜장면을 만들기 위한 최소 요리 횟수
        Arrays.fill(dp, INF);
        dp[0] = 0; // 0개의 짜장면을 만드는데 0번 필요
        // 짜장면 i개를 만들기 위한 횟수 -> a+b=i 조합
        for (int i = 1; i <= n; i++) {
            // 단일 웍
            if (wok[i] > 0) dp[i] = 1;
            for (int j = 1; j <= i/2; j++) {
                // 두 웍의 크기가 같은 경우, 웍이 두 개 이상 존재하는지
                if (j == i-j && wok[j] >= 2) dp[i] = 1;
                // 두 웍의 크기가 다른 경우, 웍이 한 개 이상 존재하는지
                else if (j != i-j && wok[j] > 0 && wok[i-j] > 0) dp[i] = 1;
                // 한 번에 조리가 불가능
                else if (dp[j] != INF && dp[i-j] != INF) dp[i] = Math.min(dp[i], dp[j]+dp[i-j]);
            }
        }

        System.out.println(dp[n] == INF ? -1 : dp[n]);

        br.close();
    }
}