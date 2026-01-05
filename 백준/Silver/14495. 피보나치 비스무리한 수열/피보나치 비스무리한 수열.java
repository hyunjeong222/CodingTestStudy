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

        long[] dp = new long[117];
        dp[1] = 1; dp[2] = 1; dp[3] = 1;
        for (int i = 4; i <= 116; i++) {
            dp[i] = dp[i-1]+dp[i-3];
        }

        System.out.println(dp[n]);

        br.close();
    }
}