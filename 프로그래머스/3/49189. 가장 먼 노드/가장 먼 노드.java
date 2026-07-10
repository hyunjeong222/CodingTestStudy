import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int ans = 0; // 1번 노드로부터 가장 멀리 떨어진 노드 개수
        
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < edge.length; i++) {
            list.get(edge[i][0]).add(edge[i][1]);
            list.get(edge[i][1]).add(edge[i][0]);
        }

        int[] dis = new int[n+1];
        Arrays.fill(dis, n+1);

        Queue<Integer> que = new LinkedList<>();
        que.offer(1);
        dis[1] = 0;
        while (!que.isEmpty()) {
            int now = que.poll();

            for (int next : list.get(now)) {
                if (dis[next] > dis[now]+1) {
                    dis[next] = dis[now]+1;
                    que.offer(next);
                }
            }
        }
        
        Arrays.sort(dis);
        int max = dis[dis.length-2];
        for (int i = 0; i < n; i++) {
            if (max == dis[i]) ans++;
        }
        
        return ans;
    }
}