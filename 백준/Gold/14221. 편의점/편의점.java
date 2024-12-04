import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    static int n, m;
    static int[] dist;
    static boolean[] checked;
    static PriorityQueue<Pos> pq;
    static ArrayList<ArrayList<Pos>> list;
    static int[] candidate, convenience; // 집의 후보지들, 편의점들
    static public class Pos implements Comparable<Pos> {
        int end; int dist;
        public Pos (int end, int dist) {
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

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken()); // 집의 후보지 개수
        int q = Integer.parseInt(st.nextToken()); // 편의점의 개수

        candidate = new int[p];
        convenience = new int[q];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < p; i++) {
            candidate[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; i++) {
            convenience[i] = Integer.parseInt(st.nextToken());
        }

        int ans = dijkstra();
        System.out.println(ans);
    }

    private static int dijkstra() {
        pq = new PriorityQueue<>();
        dist = new int[n+1];
        Arrays.fill(dist, INF);
        checked = new boolean[n+1];

        for (int num : convenience) { // 편의점을 시작점
            pq.offer(new Pos(num, 0));
            dist[num] = 0;
        }

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int end = now.end;

            if (dist[end] < now.dist) continue;
            if (checked[end]) continue;
            checked[end] = true;

            for (Pos next : list.get(end)) {
                if (dist[next.end] > dist[end] + next.dist) {
                    dist[next.end] = dist[end] + next.dist;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }

        int min = 0;
        for (int home : candidate) {
            if (dist[min] > dist[home] || (dist[min] == dist[home] && min > home)) {
                min = home;
            }
        }

        return min;
    }
}