import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, v, e;
    static int a, b;
    static int[] dist;
    static boolean[] checked;
    static ArrayList<ArrayList<Pos>> list;
    static PriorityQueue<Pos> pq;
    static public class Pos implements Comparable<Pos> {
        int e; int d;
        public Pos(int e, int d) {
            this.e = e; this.d = d;
        }
        @Override
        public int compareTo(Pos o) {
            return this.d - o.d;
        }
    }
    static final int INF = 987654321;
    static int[] starts;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 팀원의 수
        v = Integer.parseInt(st.nextToken()); // 장소의 수
        e = Integer.parseInt(st.nextToken()); // 도로의 수

        dist = new int[v+1];

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken()); // KIST 위치
        b = Integer.parseInt(st.nextToken()); // 씨알푸드 위치

        starts = new int[n]; // 팀원 집의 위치
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            starts[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }

        int u, v, d;
        while (e --> 0) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            list.get(u).add(new Pos(v, d));
            list.get(v).add(new Pos(u, d));
        }

        int ans = 0;
        for (int start : starts) {
            dijkstra(start);
            ans += dist[a] == INF ? -1 : dist[a];
            ans += dist[b] == INF ? -1 : dist[b];
        }

        System.out.println(ans);
    }

    private static void dijkstra(int start) {
        Arrays.fill(dist, INF);
        pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0));
        dist[start] = 0;

        checked = new boolean[v+1];

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int end = now.e;

            if (dist[end] < now.d) continue;
            if (checked[end]) continue;
            checked[end] = true;

            for (Pos next : list.get(end)) {
                if (dist[next.e] > dist[end]+next.d) {
                    dist[next.e] = dist[end]+next.d;
                    pq.offer(new Pos(next.e, dist[next.e]));
                }
            }
        }
    }
}