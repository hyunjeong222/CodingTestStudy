import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int MAX = 30;
    static long[][] dp = new long[MAX+1][MAX+1];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        dp();
        while (true) {
            int n = Integer.parseInt(br.readLine()); // 약의 개수
            if (n == 0) break; // 종료조건
            sb.append(dp[n][n]).append("\n");
        }
        System.out.println(sb);
    }

    private static void dp() {
        // dp[i][j] : 온전한 알약 i개, 반개의 알약 j개에서 나올 수 있는 경우의 수
        dp[1][0] = 1;
        for (int i = 0; i <= MAX; i++) { // w
            for (int j = 0; j <= MAX; j++) { // h
                if (i < MAX) dp[i+1][j] += dp[i][j];
                if (j < i) dp[i][j+1] += dp[i][j];
            }
        }
    }
}