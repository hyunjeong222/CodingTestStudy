import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k;
    static int[] stones;
    static boolean[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 돌의 개수
        k = Integer.parseInt(st.nextToken()); // 쓸 수 있는 최대 힘

        st = new StringTokenizer(br.readLine());
        stones = new int[n];
        for (int i = 0; i < n; i++) {
            stones[i] = Integer.parseInt(st.nextToken());
        }

        dp = new boolean[n+1];

        dfs(1);

        System.out.println(dp[n] ? "YES" : "NO");


        br.close();
    }

    private static void dfs(int idx) {
        if (idx == n) {
            dp[idx] = true;
            return;
        }

        if (dp[idx]) return;

        dp[idx] = true;
        for (int i = idx+1; i <= n; i++) { // 이동할 수 있는 돌 j
            if ((i-idx)*(1+Math.abs(stones[idx-1]-stones[i-1])) <= k) {
                dfs(i);
            }
        }
    }
}