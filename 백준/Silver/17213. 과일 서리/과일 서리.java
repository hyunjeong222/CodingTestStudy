import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 과일의 종류 수
        int m = Integer.parseInt(br.readLine()); // 훔치려 하는 과일의 개수

        int[][] dp = new int[11][31];
        for (int i = 1; i <= m; i++) {
            dp[1][i] = 1;
        }
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= m; j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
            }
        }

        System.out.println(dp[n][m]);

        br.close();
    }
}