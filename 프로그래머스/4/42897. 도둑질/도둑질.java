import java.util.*;

class Solution {
    public int solution(int[] money) {
        int len = money.length;
        
        int ans = 0;

        int[] dp = new int[len+1];
        dp[0] = money[0];
        // 집이 두 개만 있을 땐 큰 집을 턴다
        dp[1] = Math.max(dp[0], dp[1]);

        // 첫 집을 털 경우 (마지막 집 X)
        for (int i = 2; i < len-1; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+money[i]);
        }

        int[] dp2 = new int[len+1];
        dp2[0] = 0;
        dp2[1] = money[1];

        // 마지막 집을 털 경우 (첫 집 X)
        for (int i = 2; i < len; i++) {
            dp2[i] = Math.max(dp2[i-1], dp2[i-2]+money[i]);
        }

        int first = 0;
        for (int i : dp) {
            first = Math.max(first, i);
        }
        int second = 0;
        for (int i : dp2) {
            second = Math.max(second, i);
        }

        ans = Math.max(first, second);
        
        return ans;
    }
}