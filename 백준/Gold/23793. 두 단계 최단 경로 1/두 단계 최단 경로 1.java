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
    static public class Pos implements Comparable<Pos>{
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
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int u, v, w;
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            list.get(u).add(new Pos(v, w));
        }

        int x, y, z;
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        z = Integer.parseInt(st.nextToken());

        int result1, result2;
        dijkstra(x, -1);
        result1 = dist[y] == Integer.MAX_VALUE ? -1 : dist[y];
        dijkstra(y, -1);
        if (result1 == -1 || dist[z] == Integer.MAX_VALUE)
            result1 = -1;
        else
            result1 += dist[z];

        dijkstra(x, y);
        result2 = dist[z] == Integer.MAX_VALUE ? -1 : dist[z];

        System.out.println(result1 + " " + result2);
        br.close();
    }

    static void dijkstra(int start, int y) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Pos(start, dist[start]));

        while (!pq.isEmpty()) {
            Pos cur = pq.poll();

            if (dist[cur.end] < cur.dist)
                continue;

            for (Pos next : list.get(cur.end)) {
                if (next.end == y) continue;

                if (dist[next.end] < dist[cur.end] + next.dist)
                    continue;

                dist[next.end] = dist[cur.end] + next.dist;
                pq.offer(new Pos(next.end, dist[next.end]));
            }
        }
    }
}