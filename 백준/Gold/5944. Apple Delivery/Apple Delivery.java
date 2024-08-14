import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int c, p, pb, pa1, pa2;
    static ArrayList<ArrayList<Pos>> list;
    static PriorityQueue<Pos> pq;
    static int[] dist;
    static public class Pos implements Comparable<Pos> {
        int end; int dist;
        public Pos(int end, int dist) {
            this.end = end; this.dist = dist;
        }
        @Override
        public int compareTo(Pos o) {
            return this.dist - o.dist;
        }
    }
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken()); // 간선
        p = Integer.parseInt(st.nextToken()); // 정점
        pb = Integer.parseInt(st.nextToken()); // 시작점
        pa1 = Integer.parseInt(st.nextToken()); //
        pa2 = Integer.parseInt(st.nextToken()); //

        list = new ArrayList<>();
        for (int i = 0; i <= p; i++) {
            list.add(new ArrayList<>());
        }

        int u, v, w;
        while (c --> 0) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            list.get(u).add(new Pos(v, w));
            list.get(v).add(new Pos(u, w));
        }

        int[] pbDist = dijkstra(pb);
        int[] pa1Dist = dijkstra(pa1);
        int[] pa2Dist = dijkstra(pa2);

        int dist1 = pbDist[pa1] + pa1Dist[pa2];
        int dist2 = pbDist[pa2] + pa2Dist[pa1];

        int ans = Math.min(dist1, dist2);
        System.out.println(ans);
    }

    private static int[] dijkstra(int start) {
        pq = new PriorityQueue<>();
        dist = new int[p+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Pos(start, 0));

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int end = now.end;

            if (dist[end] < now.dist) continue;

            for (Pos next : list.get(end)) {
                if (dist[next.end] > dist[end] + next.dist) {
                    dist[next.end] = dist[end] + next.dist;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }

        return dist;
    }
}