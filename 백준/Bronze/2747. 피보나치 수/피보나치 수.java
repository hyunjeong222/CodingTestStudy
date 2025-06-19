import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dp = new int[46];
        dp[0] = 0; dp[1] = 1; dp[2] = 1;
        for (int i = 3; i < 46; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        int n = Integer.parseInt(br.readLine());
        System.out.println(dp[n]);

        br.close();
    }
}