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
    static ArrayList<ArrayList<Pos>> list;
    static PriorityQueue<Pos> pq;
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

        int a, b, t;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            list.get(a).add(new Pos(b, t));
            list.get(b).add(new Pos(a, t));
        }

        int res = 0; // 가장 오래 걸리는 시간 저장
        for (int i = 2; i < n; i++) { // 시작점, 도착점 제외
            checked = new boolean[n+1];
            checked[i] = true; // i번 도로 막음
            dijkstra(1);

            res = Math.max(res, dist[n]);
        }

        checked = new boolean[n+1];
        dijkstra(1); // 가장 최단 거리 구하기
        int shortest = dist[n];

        StringBuilder sb = new StringBuilder();
        if (res == INF) sb.append(-1).append("\n");
        else sb.append(res-shortest).append("\n");

        System.out.println(sb.toString());
    }

    private static void dijkstra(int start) {
        dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0));

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (dist[now.end] < now.dist) continue;

            if (checked[now.end]) continue;
            checked[now.end] = true;

            for (Pos next : list.get(now.end)) {
                if (!checked[next.end] && dist[next.end] > dist[now.end] + next.dist) {
                    dist[next.end] = dist[now.end] + next.dist;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }
    }
}