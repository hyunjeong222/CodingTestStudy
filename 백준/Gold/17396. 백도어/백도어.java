import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final long INF = Long.MAX_VALUE;
    static int n, m;
    static long[] dist;
    static boolean[] sight; // 적의 시야가 보이는지
    static boolean[] checked;
    static ArrayList<ArrayList<Pos>> list;
    static PriorityQueue<Pos> pq;
    static public class Pos implements Comparable<Pos> {
        int end; long dist;
        public Pos (int end, long dist) {
            this.end = end; this.dist = dist;
        }
        @Override
        public int compareTo(Pos o) {
            return Long.compare(this.dist, o.dist);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        dist = new long[n];
        Arrays.fill(dist, INF);

        sight = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int flag = Integer.parseInt(st.nextToken());
            if (flag == 1) sight[i] = true; // 1은 상대의 시야가 보임
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

        dijkstra(0);

        StringBuilder sb = new StringBuilder();
        if (dist[n-1] == INF) sb.append(-1).append("\n");
        else sb.append(dist[n-1]).append("\n");

        System.out.println(sb.toString());
    }

    private static void dijkstra(int i) {
        pq = new PriorityQueue<>();
        pq.offer(new Pos(i, 0));
        dist[i] = 0;
        checked = new boolean[n];

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (dist[now.end] < now.dist) continue;

            if (checked[now.end]) continue;
            checked[now.end] = true;

            for (Pos next : list.get(now.end)) {
                //  N-1번째 분기점은 상대의 시야에 보이게 되며,
                //  또 유일하게 상대 시야에 보이면서 갈 수 있는 곳
                if (next.end != n-1 && sight[next.end]) continue;
                if (dist[next.end] > dist[now.end] + next.dist) {
                    dist[next.end] = dist[now.end] + next.dist;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }
    }
}