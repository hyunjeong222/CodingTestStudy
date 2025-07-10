import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n+1][m+2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[n+1][m+2]; // (i,j) 위치까지 도달하는 경우의 수
        // 출발점 초기화
        for (int j = 1; j <= m; j++) {
            dp[n][j] = map[n][j];
        }

        for (int i = n-1; i >= 1; i--) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == 0) continue; // 일반 유리는 이동 불가
                dp[i][j] = (dp[i+1][j-1]+dp[i+1][j]+dp[i+1][j+1])%MOD;
            }
        }

        long ans = 0;
        for (int j = 1; j <= m; j++) {
            ans = (ans + dp[1][j]) % MOD;
        }

        System.out.println(ans);

        br.close();
    }
}