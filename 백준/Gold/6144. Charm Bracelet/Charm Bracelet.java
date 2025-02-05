import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 매력의 개수
        int m = Integer.parseInt(st.nextToken()); // 무게 제한

        int[] dp = new int[m+1];
        int w, v;
        while (n --> 0) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken()); // 이번 매력 무게
            v = Integer.parseInt(st.nextToken()); // 이번 매력 가치

            for (int i = m; w <= i; --i) {
                dp[i] = Math.max(dp[i-w]+v, dp[i]);
            }
        }

        System.out.println(dp[m]);
    }
}