import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static long[] dist;
    static final long INF = Long.MAX_VALUE;
    static ArrayList<ArrayList<Pos>> list;
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
    static PriorityQueue<Pos> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 도시의 수
        m = Integer.parseInt(st.nextToken()); // 도로의 수
        k = Integer.parseInt(st.nextToken()); // 면접장의 수

        list = new ArrayList<>();
        for (int i = 0; i <= n ; i++) {
            list.add(new ArrayList<>());
        }

        int u, v, w;
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            // 어떠한 면접장이든 하나의 도시로는 갈 수 있다 -> 역방향으로 변경
            list.get(v).add(new Pos(u, w));
        }

        dist = new long[n+1];
        Arrays.fill(dist, INF);
        pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            dist[tmp] = 0;
            pq.offer(new Pos(tmp, 0));
        }

        dijkstra();

        int maxNum = 0; long maxDist = 0;
        for (int i = 1; i <= n; i++) {
            if (maxDist < dist[i]) {
                maxNum = i;
                maxDist = dist[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(maxNum).append("\n").append(maxDist);
        System.out.println(sb.toString());
    }

    private static void dijkstra() {
        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int end = now.end;

            if (dist[end] < now.dist) continue;

            for (Pos next : list.get(now.end)) {
                if (dist[next.end] > dist[now.end] + next.dist) {
                    dist[next.end] = dist[now.end] + next.dist;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }
    }
}