import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Pos>> list;
    static PriorityQueue<Pos> pq;
    static int[] dist;
    static boolean[] checked;
    static public class Pos implements Comparable<Pos> {
        int end; int dist;
        public Pos(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
        @Override
        public int compareTo(Pos o) {
            return this.dist - o.dist;
        }
    }
    static final int INF = Integer.MAX_VALUE;
    static int[][] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.get(u).add(new Pos(v, w));
            list.get(v).add(new Pos(u, w));
        }

        StringBuilder sb = new StringBuilder();
        ans = new int[n][n];
        for (int i = 1; i <= n; i++) {
            dijkstra(i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) sb.append("-").append(" ");
                else sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dijkstra(int start) {
        dist = new int[n+1];
        Arrays.fill(dist, INF);
        checked = new boolean[n+1];
        pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int end = now.end;

            if (dist[end] < now.dist) continue;
            if (checked[end]) continue;
            checked[end] = true;

            for (Pos next : list.get(end)) {
                if (!checked[next.end] && dist[next.end] > dist[end]+next.dist) {
                    dist[next.end] = dist[end]+next.dist;
                    ans[next.end-1][start-1] = end;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }
    }
}