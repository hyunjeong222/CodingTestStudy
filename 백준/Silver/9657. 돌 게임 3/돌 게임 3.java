import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 돌의 개수

        // StringTokenizer st = new StringTokenizer(br.readLine());

        // 상근이가 이기는 경우 1, 지는 경우 0
        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 0;
        dp[3] = 1;
        dp[4] = 1;
        for (int i = 5; i <= n; i++) {
            if (dp[i-1]+dp[i-3]+dp[i-4] < 3) dp[i] = 1;
            else dp[i] = 0;
        }

        System.out.println(dp[n] == 1 ? "SK" : "CY");

        br.close();
    }
}