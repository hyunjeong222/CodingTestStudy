import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r;
    static int[] t;
    static int[] dist;
    static boolean[] checked;
    static ArrayList<ArrayList<Pos>> list;
    static PriorityQueue<Pos> pq;
    static public class Pos implements Comparable<Pos>{
        int ed; int dist;
        public Pos(int ed, int dist) {
            this.ed = ed; this.dist = dist;
        }
        @Override
        public int compareTo(Pos o) {
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 지역의 개수
        m = Integer.parseInt(st.nextToken()); // 수색 범위
        r = Integer.parseInt(st.nextToken()); // 길이의 개수
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        t = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            t[i] = Integer.parseInt(st.nextToken());
        }
        int u, v, w;
        while (r --> 0) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            // 양방향
            list.get(u).add(new Pos(v, w));
            list.get(v).add(new Pos(u, w));
        }
        dist = new int[n+1];
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dijkstra(i));
        }
        System.out.println(ans);
    }

    private static int dijkstra(int start) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0));
        checked = new boolean[n+1];
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int end = now.ed;

            if (checked[end]) continue;
            checked[end] = true;

            if (dist[end] < now.dist) continue;

            for (Pos next : list.get(end)) {
                if (checked[next.ed] || dist[next.ed] < dist[end] + next.dist) continue;

                dist[next.ed] = dist[end] + next.dist;
                pq.offer(new Pos(next.ed, dist[next.ed]));
            }
        }

        int item = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] <= m) item += t[i];
        }

        return item;
    }
}