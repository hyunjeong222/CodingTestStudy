class Solution {
    static final int MOD = 1234567;
    public int solution(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0; dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = (dp[i-2] + dp[i-1]) % MOD;
        }
        
        int answer = dp[n] % MOD;
        return answer;
    }
}