import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, d, c;
    static ArrayList<ArrayList<Pos>> list;
    static int[] dist;
    static boolean[] checked;
    static PriorityQueue<Pos> pq;
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
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            dist = new int[n+1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }

            while (d --> 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                list.get(b).add(new Pos(a, s));
            }

            Dijkstra(c);
            int cnt = 0;
            int ans = 0;
            for (int i = 1; i <= n; i++) {
                if (dist[i] != Integer.MAX_VALUE) {
                    cnt++;
                    ans = Math.max(ans, dist[i]);
                }
            }
            sb.append(cnt).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void Dijkstra(int x) {
        checked = new boolean[n+1];
        pq = new PriorityQueue<>();
        pq.offer(new Pos(x, 0));
        dist[x] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int end = now.end;

            if (dist[end] < now.dist) continue;
            if (checked[end]) continue;
            checked[end] = true;

            for (Pos next : list.get(end)) {
                if (dist[next.end] > dist[end]+next.dist) {
                    dist[next.end] = dist[end]+next.dist;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }
    }
}