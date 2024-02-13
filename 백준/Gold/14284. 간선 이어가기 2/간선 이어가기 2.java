import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] dist;
    static boolean[] checked;
    static PriorityQueue<Pos> pq;
    static ArrayList<ArrayList<Pos>> list;
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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list.get(u).add(new Pos(v, d));
            list.get(v).add(new Pos(u, d));
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        System.out.println(dijkstra(s,t));
    }

    private static int dijkstra(int s, int t) {
        pq = new PriorityQueue<>();
        checked = new boolean[n+1];
        pq.offer(new Pos(s, 0));
        dist[s] = 0;
        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int end = now.end;
            if (checked[end]) continue;
            if (now.end == t) break;
            checked[end] = true;
            for (Pos next : list.get(end)) {
                if (!checked[next.end] && dist[next.end] > dist[end] + next.dist) {
                    dist[next.end] = dist[end] + next.dist;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }
        return dist[t];
    }
}