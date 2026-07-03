import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        for (int[] i : puddles) {
            dp[i[1]][i[0]] = -1;
        }

        dp[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (dp[i][j] == -1) continue; // 웅덩이

                if (dp[i-1][j] > 0) {
                    dp[i][j] = (dp[i][j] + dp[i-1][j])%MOD;
                }
                if (dp[i][j-1] > 0) {
                    dp[i][j] = (dp[i][j] + dp[i][j-1])%MOD;
                }
            }
        }


        int ans = dp[n][m]; // 채울 수 있는 가장 많은 조각 수
        
        return ans;
    }
}