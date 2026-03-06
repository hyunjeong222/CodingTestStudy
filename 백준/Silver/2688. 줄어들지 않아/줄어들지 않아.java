import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        long[][] dp = new long[65][10]; // dp[i][j] : i는 자리 수, j는 시작하는 수
        // 1자리는 모두 1개
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i < 65; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j; k < 10; k++) {
                    dp[i][j] += dp[i-1][k];
                }
            }
        }

        int t = Integer.parseInt(br.readLine());
        while (t --> 0){
            int n = Integer.parseInt(br.readLine()); // 자릿수

            long ans = 0;
            for (int i = 0; i < 10; i++) {
                ans += dp[n][i];
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}