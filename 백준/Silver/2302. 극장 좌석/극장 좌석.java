import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 좌석의 개수

        int m = Integer.parseInt(br.readLine()); // 고정석의 개수

        int[] dp = new int[41];
        dp[0] = 1; dp[1] = 1; dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        int ans = 1; // n == 1일 때, ans = 1

        // vip 좌석을 제외한 나머지 좌석의 경우의 수를 서로 곱함
        // A, B, C는 같은 시간에 일어나는 사건
        int before = 0;
        for (int i = 0; i < m; i++) {
            int vip = Integer.parseInt(br.readLine());
            ans *= dp[vip-before-1];
            before = vip;
        }
        // 마지막 vip 좌석 다음 좌석에서 끝 좌석까지의 경우의 수
        ans *= dp[n-before];

        System.out.println(ans);

        br.close();
    }
}