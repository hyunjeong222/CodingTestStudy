import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, a, b, c;
    static ArrayList<ArrayList<Pos>> list;
    static public class Pos implements Comparable<Pos> {
        int end; int dist; int shy;
        public Pos (int end, int dist) {
            this.end = end; this.dist = dist;
        }
        public Pos (int end, int dist, int shy) {
            this.end = end; this.dist = dist; this.shy = shy;
        }
        @Override
        public int compareTo(Pos o) {
            return this.shy - o.shy;
        }
    }
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 교차로 개수
        m = Integer.parseInt(st.nextToken()); // 골목 개수
        a = Integer.parseInt(st.nextToken()); // 시작 교차로 번호
        b = Integer.parseInt(st.nextToken()); // 도착 교차로 번호
        c = Integer.parseInt(st.nextToken()); // 가진 돈

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

        int ans = dijkstra(a);

        System.out.println(ans);
    }

    private static int dijkstra(int a) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(a, 0, 0));
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[a] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            // 도착점
            if (now.end == b) return dist[now.end];

            if (dist[now.end] < now.shy) continue;

            for (Pos next : list.get(now.end)) {
                if (now.dist + next.dist > c) continue;

                if (dist[next.end] > Math.max(dist[now.end], next.dist)) {
                    dist[next.end] = Math.max(dist[now.end], next.dist);
                    pq.offer(new Pos(next.end, next.dist+now.dist, dist[next.end]));
                }
            }
        }

        return -1;
    }
}