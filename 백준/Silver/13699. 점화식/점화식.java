import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[36];
        dp[0] = 1; dp[1] = 1;
        for (int i = 2; i <= 35; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += (dp[j]*dp[i-j-1]);
            }
        }

        System.out.println(dp[n]);

        br.close();
    }
}