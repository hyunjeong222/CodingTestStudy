import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, a, b;
    static long c;
    static ArrayList<ArrayList<Pos>> list;
    static public class Pos implements Comparable<Pos> {
        int end; long dist; long shy;
        public Pos(int end, long dist) {
            this.end = end; this.dist = dist;
        }
        public Pos(int end, long dist, long shy) {
            this.end = end; this.dist = dist; this.shy = shy;
        }
        @Override
        public int compareTo(Pos o) {
            return Long.compare(this.shy, o.shy);
        }
    }
    static final Long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 교차로 개수
        m = Integer.parseInt(st.nextToken()); // 골목 개수
        a = Integer.parseInt(st.nextToken()); // 시작 교차로 번호
        b = Integer.parseInt(st.nextToken()); // 도착 교차로 번호
        c = Long.parseLong(st.nextToken()); // 가진 돈

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        int u, v, w;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            list.get(u).add(new Pos(v, w));
            list.get(v).add(new Pos(u, w));
        }

        System.out.println(dijkstra(a));
    }

    private static long dijkstra(int a) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(a, 0, 0));
        long[] dist = new long[n+1];
        Arrays.fill(dist, INF);
        dist[a] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (now.end == b) return dist[b];

            if (dist[now.end] < now.shy) continue;

            for (Pos next : list.get(now.end)) {
                if (now.dist + next.dist > c) continue;

                if (dist[next.end] > Math.max(dist[now.end], next.dist)) {
                    dist[next.end] = Math.max(dist[now.end], next.dist);
                    pq.offer(new Pos(next.end, now.dist + next.dist, dist[next.end]));
                }
            }
        }
        return -1;
    }
}