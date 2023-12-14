import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int u, v, k;
    static int[] dist;
    static boolean[] checked;
    static PriorityQueue<pos> pq;
    static ArrayList<ArrayList<pos>> list;
    static public class pos implements Comparable<pos>{
        int end; int dist;
        public pos(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
        @Override
        public int compareTo(pos o) {
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            list.get(u).add(new pos(v, k));
            list.get(v).add(new pos(u, k));
        }
        checked = new boolean[n+1];
        dijkstra(1);
        bw.append(dist[n] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dijkstra(int end) {
        pq = new PriorityQueue<>();
        pq.offer(new pos(end, 0));
        dist[end] = 0;

        while (!pq.isEmpty()) {
            pos now = pq.poll();
            end = now.end;
            checked[end] = true;

            for (pos next : list.get(end)) {
                if (!checked[next.end] && dist[next.end] > dist[end]+next.dist) {
                    dist[next.end] = dist[end]+next.dist;
                    pq.offer(new pos(next.end, dist[next.end]));
                }
            }
        }
    }
}