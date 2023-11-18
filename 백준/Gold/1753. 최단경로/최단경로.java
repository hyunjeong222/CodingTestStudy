import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static ArrayList<ArrayList<pos>> list;
    static int[] dist;
    static boolean[] checked;
    static PriorityQueue<pos> pq;
    static public class pos implements Comparable<pos> {
        int end; int dist;
        public pos (int end, int dist) {
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
        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        m = Integer.parseInt(st.nextToken()); // 간선의 개수

        k = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            // u -> v
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 가중치
            int w = Integer.parseInt(st.nextToken());

            list.get(u).add(new pos(v, w));
        }

        dijkstra(k);

        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) bw.append("INF" + "\n");
            else bw.append(dist[i] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dijkstra(int start) {
        pq = new PriorityQueue<>();
        checked = new boolean[n+1];
        pq.offer(new pos(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            pos now = pq.poll();
            int end = now.end;
            checked[end] = true;

            for (pos next : list.get(end)) {
                if (!checked[next.end] && dist[next.end] > dist[end] + next.dist) {
                    dist[next.end] = dist[end] + next.dist;
                    pq.offer(new pos(next.end, dist[next.end]));
                }
            }
        }
    }
}