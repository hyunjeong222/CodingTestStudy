import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, e, u, v;
    static PriorityQueue<Pos> pq;
    static ArrayList<ArrayList<Pos>> list;
    static int[] dist;
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
    static final int INF = 200_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        e = Integer.parseInt(st.nextToken()); // 간선의 개수

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()); // 가중치
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Pos(b, c));
            list.get(b).add(new Pos(a, c));
        }

        // 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호
        st = new StringTokenizer(br.readLine());
        u = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        int distA = 0; // u -> v일 때 최단거리
        distA += dijkstra(1, u);
        distA += dijkstra(u, v);
        distA += dijkstra(v, n);

        int distB = 0; // v -> u일 때 최단거리
        distB += dijkstra(1, v);
        distB += dijkstra(v, u);
        distB += dijkstra(u, n);

        System.out.println((distA >= INF && distB >= INF) ? -1 : Math.min(distA, distB));

        br.close();
    }

    private static int dijkstra(int start, int end) {
        dist = new int[n+1];
        Arrays.fill(dist, INF);

        pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0));

        dist[start] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (now.cost > dist[now.to]) continue;

            for (Pos next : list.get(now.to)) {
                if (dist[next.to] > dist[now.to] + next.cost) {
                    dist[next.to] = dist[now.to] + next.cost;
                    pq.offer(new Pos(next.to, dist[next.to]));
                }
            }
        }

        return dist[end];
    }
}