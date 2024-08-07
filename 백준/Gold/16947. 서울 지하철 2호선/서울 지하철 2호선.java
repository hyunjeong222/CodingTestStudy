import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] visited;
    static boolean isCycle;
    static int[] distance;
    static Queue<Integer> que = new LinkedList<Integer>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        visited = new boolean[n+1];
        distance = new int[n+1];
        Arrays.fill(distance, -1);

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

        // 순환선 찾기
        dfs(list, 0, 1);
        // 각 역과 순환선의 거리 구하기
        bfs(list);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(distance[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static void bfs(ArrayList<ArrayList<Integer>> list) {
        int cnt = 1;
        while (!que.isEmpty()) {
            int len = que.size();
            for (int j = 0; j < len; j++) {
                int tmp = que.poll();
                for (int i : list.get(tmp)) {
                    if (distance[i] != -1) continue;
                    distance[i] = cnt;
                    que.add(i);
                }
            }
            cnt++;
        }
    }

    private static void dfs(ArrayList<ArrayList<Integer>> list, int prev, int now) {
        visited[now] = true;
        for (int next : list.get(now)) {
            if (visited[next] && next != prev) {
                isCycle = true;
                distance[next] = 0;
                que.add(next);
                break;
            } else if (!visited[next]) {
                dfs(list, now, next);
                if (isCycle) {
                    if (distance[next] == 0) {
                        isCycle = false;
                    } else {
                        distance[next] = 0;
                        que.add(next);
                    }
                    return;
                }
            }
        }
    }
}