import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] dp = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 여기서 대각선으로 가는 경로는 오른쪽->아래와 아래->오른쪽을 거쳐가는 것보다
                // 항상 작을 수밖에 없다.
                dp[i][j] = Math.max(map[i][j]+dp[i-1][j],map[i][j]+dp[i][j-1]);
            }
        }

        System.out.println(dp[n][m]);

        br.close();
    }
}