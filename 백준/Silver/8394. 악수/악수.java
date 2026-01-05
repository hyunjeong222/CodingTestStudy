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

        long[] dp = new long[10000001];
        dp[1] = 0; dp[2] = 2; dp[3] = 3;
        for (int i = 4; i <= 10000000; i++) {
            dp[i] = (dp[i-1]+dp[i-2])%10;
        }

        System.out.println(dp[n]);

        br.close();
    }
}