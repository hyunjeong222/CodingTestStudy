import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k, x;
    static ArrayList<ArrayList<Pos>> list;
    static int[] dist;
    // static boolean[] checked;
    static public class Pos implements Comparable<Pos>{
        int to; int cost;
        public Pos (int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Pos o) {
            return this.cost - o.cost;
        }
    }
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 도시의 개수
        m = Integer.parseInt(st.nextToken()); // 도로의 개수
        k = Integer.parseInt(st.nextToken()); // 거리의 정보
        x = Integer.parseInt(st.nextToken()); // 출발 도시의 번호

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        dist = new int[n+1];
        Arrays.fill(dist, INF);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작점
            int b = Integer.parseInt(st.nextToken()); // 목적지

            list.get(a).add(new Pos(b, 1));
        }

        dijkstra(x);

        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == k) {
                sb.append(i).append("\n");
            }
        }

        if (sb.length() == 0) sb.append(-1).append("\n");

        System.out.println(sb.toString());

        br.close();
    }

    private static void dijkstra(int start) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        // checked = new boolean[n+1];

        pq.offer(new Pos(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int node = now.to;
            // checked[node] = true;

            if (now.cost > dist[now.to]) continue;

            for (Pos next : list.get(node)) {
                // !checked[next.to] &&
                if (dist[next.to] > dist[node] + next.cost) {
                    dist[next.to] = dist[node] + next.cost;
                    pq.offer(new Pos(next.to, dist[next.to]));
                }
            }
        }
    }
}