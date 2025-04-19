import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static double[] dist;
    static ArrayList<ArrayList<Pos>> list;
    static public class Pos implements Comparable<Pos> {
        int end, cost, start;
        double time;
        public Pos (int end, int cost, int start) {
            this.end = end; this.cost = cost; this.start = start;
        }

        public Pos(int end, double time) {
            this.end = end; this.time = time;
        }

        @Override
        public int compareTo(Pos o) {
            return Double.compare(this.time, o.time);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 역의 수
        m = Integer.parseInt(st.nextToken()); // 노선 수

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        dist = new double[n+1];
        Arrays.fill(dist, Double.MAX_VALUE);

        int a, b, t, w;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken()); // 소요되는 시간
            w = Integer.parseInt(st.nextToken()); // 열차 출발 시간

            // 단방향 노선
            list.get(a).add(new Pos(b, t, w));
        }

        dijkstra(1); // 1번 역에서 12시에 출발

        System.out.println((int)dist[n]);

        br.close();
    }

    private static void dijkstra(int x) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(x, 0));
        dist[1] = 0;

        int nowNode; double nowTime;
        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            nowNode = now.end;
            nowTime = now.time;

            if (nowTime > dist[nowNode]) continue;

            for (Pos next : list.get(nowNode)) {
                double startTime = Math.ceil(nowTime / next.start) * next.start;
                double nextTime = startTime + next.cost;

                if (dist[next.end] > nextTime) {
                    dist[next.end] = nextTime;
                    pq.offer(new Pos(next.end, nextTime));
                }
            }
        }
    }
}