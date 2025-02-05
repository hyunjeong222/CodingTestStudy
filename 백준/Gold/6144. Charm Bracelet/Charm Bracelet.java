import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 매력의 개수
        int m = Integer.parseInt(st.nextToken()); // 최대 용량

        int[] w = new int[n+1]; // 무게
        int[] v = new int[n+1]; // 가치
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (w[i] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]]+v[i]);
                } else dp[i][j] = dp[i-1][j];
            }
        }

        System.out.println(dp[n][m]);
    }
}