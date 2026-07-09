import java.util.*;

class Solution {
    static boolean[] visited;
    static int ans = -1; // 유저가 탐험할수 있는 최대 던전 수
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(0, k, dungeons);
        
        return ans;
    }
    
    private static void dfs(int cnt, int k, int[][] dungeons) {
        for (int i = 0; i < dungeons.length; i++) {
            // 이미 참여한 던전이거나 최소 필요 피로도보다 현재 피로도가 낮다면 패스
            if (visited[i] || dungeons[i][0] > k) continue;
            visited[i] = true;
            dfs(cnt+1, k-dungeons[i][1], dungeons);
            visited[i] = false;
        }

        ans = Math.max(ans, cnt);
    }
}