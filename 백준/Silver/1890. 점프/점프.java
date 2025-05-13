import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 경로의 개수 263-1
        // int 범위 2-31~231
        long[][] dp = new long[n][n];
        dp[0][0] = 1;
        int jump = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                jump = map[i][j];

                // 0은 더 이상 진행을 막는 종착점
                if (jump == 0) break;
                if (j+jump < n) dp[i][j+jump] += dp[i][j];
                if (i+jump < n) dp[i+jump][j] += dp[i][j];

            }
        }

        System.out.println(dp[n-1][n-1]);

        br.close();
    }
}