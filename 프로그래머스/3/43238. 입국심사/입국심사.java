class Solution {
    public long solution(int n, int[] times) {
        long ans = 0;
        
        int len = times.length;
        
        long left = 0;
        long right = (long) times[len-1]*n; // 가장 비효율적으로 심사를 받는데 걸리는 시간
        while (left <= right) {
            long mid = (left+right)/2; // 모든 사람이 심사를 받는데 걸리는 시간

            long complete = 0; // 심사를 완료한 사람 수
            for (int i = 0; i < len; i++) {
                complete += mid/times[i];
            }
            
            if (complete < n) left = mid+1;
            else {
                right = mid-1;
                ans = mid;
            }
        }
        
        return ans;
    }
}