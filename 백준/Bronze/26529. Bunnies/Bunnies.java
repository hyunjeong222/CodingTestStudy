import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[46];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= 45; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }

        while (n --> 0) {
            int x = Integer.parseInt(br.readLine());
            sb.append(dp[x]).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}