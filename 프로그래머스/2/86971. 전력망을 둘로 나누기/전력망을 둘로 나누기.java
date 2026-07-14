import java.util.*;

class Solution {
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> list;
    
    public int solution(int n, int[][] wires) {
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < wires.length; i++) {
            list.get(wires[i][0]).add(wires[i][1]);
            list.get(wires[i][1]).add(wires[i][0]);
        }
        
        
        int ans = Integer.MAX_VALUE; // 송전탑 개수의 차이

        for (int i = 0; i < wires.length; i++) {
            visited = new boolean[n+1];

            // 전선 하나 끊기
            list.get(wires[i][0]).remove(Integer.valueOf(wires[i][1]));
            list.get(wires[i][1]).remove(Integer.valueOf(wires[i][0]));

            int cnt = dfs(1);
            int diff = Math.abs(cnt-(n-cnt));
            ans = Math.min(ans, diff);

            list.get(wires[i][0]).add(wires[i][1]);
            list.get(wires[i][1]).add(wires[i][0]);
        }
        
        return ans;
    }
    
    private static int dfs(int i) {
        visited[i] = true;
        int cnt = 1; // 송전탑 개수
        for (int next : list.get(i)) {
            if (visited[next]) continue;
            cnt += dfs(next);
        }

        return cnt;
    }
}