import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int t, c, ts, te;
    static ArrayList<ArrayList<Pos>> list;
    static PriorityQueue<Pos> pq;
    static int[] dist;
    static boolean[] checked;
    static final int INF = 987654321;
    static public class Pos implements Comparable<Pos> {
        int end; int dist;
        public Pos (int end, int dist) {
            this.end = end; this.dist = dist;
        }
        @Override
        public int compareTo(Pos o) {
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        ts = Integer.parseInt(st.nextToken());
        te = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= t; i++) {
            list.add(new ArrayList<>());
        }

        int u, v, w;
        for (int i = 0; i < c; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            list.get(u).add(new Pos(v, w));
            list.get(v).add(new Pos(u, w));
        }

        int ans = dijkstra(ts);

        System.out.println(ans);
    }

    private static int dijkstra(int start) {
        dist = new int[t+1];
        Arrays.fill(dist, INF);

        pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0));
        dist[start] = 0;

        checked = new boolean[t+1];

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (now.dist > dist[now.end]) continue;
            if (checked[now.end]) continue;

            checked[now.end] = true;

            for (Pos next : list.get(now.end)) {
                if (dist[next.end] > dist[now.end] + next.dist) {
                    dist[next.end] = dist[now.end] + next.dist;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }

        return dist[te];
    }
}