import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, x, y;
    static ArrayList<ArrayList<Pos>> list;
    static int[] dist;
    static boolean[] checked;
    static PriorityQueue<Pos> pq;
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
        x = Integer.parseInt(st.nextToken()); // 왕복할 수 있는 거리
        y = Integer.parseInt(st.nextToken()); // 시작점
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        int a, b, c;
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            list.get(a).add(new Pos(b, c));
            list.get(b).add(new Pos(a, c));
        }
        dijkstra(y);
        Arrays.sort(dist);
        int total = 0;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            if (dist[i]*2 > x) {
                ans = -1;
                break;
            }
            total += dist[i]*2;
            if (total > x) {
                ans++;
                total = dist[i]*2;
            }
        }
        System.out.println(ans);
    }

    private static void dijkstra(int y) {
        dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        checked = new boolean[n];

        pq = new PriorityQueue<>();
        pq.offer(new Pos(y, 0));
        dist[y] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int end = now.end;

            if (checked[end]) continue;
            checked[end] = true;

            for (Pos next : list.get(end)) {
                if (dist[next.end] > dist[end] + next.dist) {
                    dist[next.end] = dist[end] + next.dist;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }
    }
}