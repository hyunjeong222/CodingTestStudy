import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, e;
    static int u, v;
    static ArrayList<ArrayList<pos>> list;
    static int[] dist;
    static boolean[] checked;
    static PriorityQueue<pos> pq;

    static public class pos implements Comparable<pos> {
        int end;
        int dist;

        public pos(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(pos o) {
            return this.dist - o.dist;
        }
    }
    static int INF = 200000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        e = Integer.parseInt(st.nextToken()); // 간선의 개수

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            // a -> b 양방향
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 그 거리가 c
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new pos(b, c));
            list.get(b).add(new pos(a, c));
        }

        // 반드시 거쳐야 하는 두 개의 서로 다른 정점
        st = new StringTokenizer(br.readLine());
        u = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        int distA = 0; // u -> v일때 최단거리
        distA += dijkstra(1, u);
        distA += dijkstra(u, v);
        distA += dijkstra(v, n);

        int distB = 0; // v -> u일때 최단거리
        distB += dijkstra(1, v);
        distB += dijkstra(v, u);
        distB += dijkstra(u, n);

        int ans = (distA >= INF && distB >= INF) ? -1 : Math.min(distA, distB);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dijkstra(int start, int end) {
        dist = new int[n+1];
        Arrays.fill(dist, INF);

        pq = new PriorityQueue<>();
        pq.offer(new pos(start, 0));
        checked = new boolean[n+1];
        dist[start] = 0;

        while (!pq.isEmpty()) {
            pos now = pq.poll();
            checked[now.end] = true;

            for (pos next : list.get(now.end)) {
                if (!checked[next.end] && dist[next.end] > dist[now.end] + next.dist) {
                    dist[next.end] = dist[now.end] + next.dist;
                    pq.offer(new pos(next.end, dist[next.end]));
                }
            }
        }
        return dist[end];
    }
}