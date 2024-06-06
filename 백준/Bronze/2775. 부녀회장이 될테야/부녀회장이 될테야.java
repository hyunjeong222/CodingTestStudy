import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] dp = new int[15][15];
        for (int i = 0; i < 15; i++) {
            dp[i][1] = 1; // i층 1호
            dp[0][i] = i; // 0층 i호
        }
        for (int i = 1; i < 15; i++) { // 1층부터 14층
            for (int j = 2; j < 15; j++) { // 2호부터 14호
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            int a = Integer.parseInt(br.readLine());
            int b = Integer.parseInt(br.readLine());
            sb.append(dp[a][b]).append("\n");
        }
        System.out.println(sb);
    }
}