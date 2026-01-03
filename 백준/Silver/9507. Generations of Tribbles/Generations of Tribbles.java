import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        long[] dp = new long[70];
        dp[0] = 1; dp[1] = 1; dp[2] = 2; dp[3] = 4;
        for (int i = 4; i < 70; i++) {
            dp[i] = dp[i-1]+dp[i-2]+dp[i-3]+dp[i-4];
        }

        int t = Integer.parseInt(br.readLine());
        while (t --> 0) {
            int n = Integer.parseInt(br.readLine());

            sb.append(dp[n]).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}