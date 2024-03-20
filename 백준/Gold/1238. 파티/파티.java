import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, x;
    static ArrayList<ArrayList<Pos>> reverseList;
    static ArrayList<ArrayList<Pos>> list;
    static int[] reverseDist;
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken()); // 목적지

        reverseList = new ArrayList<>();
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            reverseList.add(new ArrayList<>());
            list.add(new ArrayList<>());
        }

        int u, v, w;
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            reverseList.get(v).add(new Pos(u, w));
            list.get(u).add(new Pos(v, w));
        }

        reverseDist = new int[n+1];
        Arrays.fill(reverseDist, Integer.MAX_VALUE);
        Dijkstra(reverseList, reverseDist, x);
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Dijkstra(list, dist, x);

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, reverseDist[i]+dist[i]);
        }
        System.out.println(ans);
    }

    private static void Dijkstra(ArrayList<ArrayList<Pos>> list, int[] dist, int start) {
        checked = new boolean[n+1];
        pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            int end = pq.poll().end;

            if (checked[end]) continue;
            checked[end] = true;

            for (Pos next : list.get(end)) {
                if (dist[next.end] > dist[end]+ next.dist) {
                    dist[next.end] = dist[end]+ next.dist;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }
    }
}