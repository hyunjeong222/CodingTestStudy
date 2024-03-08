import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static int[][] dist;
    static public class Pos {
        int node; int dist;
        public Pos(int node, int dist) {
            this.node = node; this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st;
        int u, v;
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }
        dist = new int[3][n+1];
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        bfs(0, a); // 윤
        bfs(1, b); // 달구
        bfs(2, c); // 포닉스

        for (int i = 1; i <= n; i++) {
            if (graph[i].size() == 1 && dist[0][i] < dist[1][i] && dist[0][i] < dist[2][i]) {
                System.out.println("YES");
                System.exit(0);
            }
        }
        System.out.println("NO");
    }
    private static void bfs(int idx, int node) {
        boolean[] checked = new boolean[graph.length];
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(node, 0));
        checked[node] = true;
        while (!que.isEmpty()) {
            Pos now = que.poll();
            int num = now.node; int dst = now.dist;
            dist[idx][num] = dst;

            for (int next : graph[num]) {
                if (!checked[next]) {
                    checked[next] = true;
                    que.offer(new Pos(next, dst+1));
                }
            }
        }
    }
}