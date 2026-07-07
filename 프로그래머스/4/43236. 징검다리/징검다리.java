import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int ans = 0; // 각 지점 사이의 거리의 최솟값 중에 가장 큰 값

        Arrays.sort(rocks);

        int left = 0;
        int right = distance;
        while (left <= right) {
            // 각 지점 사이의 거리
            int mid = (left+right)/2;

            if (getRemoveRockCnt(rocks, mid, distance) <= n) {
                ans = mid;
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        
        return ans;
    }
    
    private static int getRemoveRockCnt(int[] rocks, int mid, int distance) {
        int before = 0;

        int removeCnt = 0;

        for (int i = 0; i < rocks.length; i++) {
            if (rocks[i]-before < mid) {
                removeCnt++;
                continue;
            }
            before = rocks[i];
        }

        if (distance-before < mid) removeCnt++;

        return removeCnt;
    }
}