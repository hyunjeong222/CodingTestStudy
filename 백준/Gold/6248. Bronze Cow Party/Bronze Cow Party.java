import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<ArrayList<Pos>> list;
    static int[] dist;
    static PriorityQueue<Pos> pq;
    static public class Pos implements Comparable<Pos> {
        int node; int cost;
        public Pos (int node, int cost) {
            this.node = node; this.cost = cost;
        }
        @Override
        public int compareTo(Pos o) {
            return this.cost-o.cost;
        }
    }
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int a, b, t;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            list.get(a).add(new Pos(b, t));
            list.get(b).add(new Pos(a, t));
        }

        dijkstra(x);
        // System.out.println(Arrays.toString(dist));

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dist[i]);
        }

        System.out.println(max*2);

        br.close();
    }

    private static void dijkstra(int start) {
        pq = new PriorityQueue<>();
        dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Pos(start, 0));

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (now.cost > dist[now.node]) continue;

            for (Pos next : list.get(now.node)) {
                if (dist[next.node] > dist[now.node]+next.cost) {
                    dist[next.node] = dist[now.node]+next.cost;
                    pq.offer(new Pos(next.node, dist[next.node]));
                }
            }
        }
    }
}