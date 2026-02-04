import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 링크 : https://january-diary.tistory.com/entry/BOJ-2133-%ED%83%80%EC%9D%BC-%EC%B1%84%EC%9A%B0%EA%B8%B0-JAVA
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine()); // 3*n

        // n이 홀수라면 타일을 채울 수 없음
        if (n%2 != 0) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[n+1];
        dp[0] = 1; dp[1] = 0; dp[2] = 3;
        for (int i = 4; i <= n; i+=2) { // 홀수는 될 수 없기 때문에 2씩 증가
            dp[i] = dp[i-2]*dp[2];

            // n = 4부터 특수 타일 2개
            for (int j = i-4; j >= 0; j-=2) {
                dp[i] += dp[j]*2;
            }
        }

        System.out.println(dp[n]);

        br.close();
    }
}