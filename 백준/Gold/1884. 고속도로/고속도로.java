import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int k, n, r;
    static ArrayList<ArrayList<Pos>> list;
    static int[][] dist;
    static public class Pos implements Comparable<Pos>{
        // 도착 정점, 길이, 통행료
        int to; int dist; int cost;
        public Pos (int to, int dist, int cost) {
            this.to = to;
            this.dist = dist;
            this.cost = cost;
        }
        @Override
        public int compareTo(Pos o) {
            // 1) 거리 기준 우선
            if (this.dist != o.dist) return this.dist - o.dist;
            // 2) 거리가 같으면 비용 적은 것 우선 (선택적)
            return this.cost - o.cost;
        }
    }
    static final int INF = Integer.MAX_VALUE;
    static int ans = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        k = Integer.parseInt(br.readLine()); // 준비해 둔 교통비

        n = Integer.parseInt(br.readLine()); // 도시의 숫자
        r = Integer.parseInt(br.readLine()); // 도로의 숫자

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        StringTokenizer st;
        int s, d, l, t;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()); // 출발 도시
            d = Integer.parseInt(st.nextToken()); // 도착 도시
            l = Integer.parseInt(st.nextToken()); // 도로의 길이
            t = Integer.parseInt(st.nextToken()); // 도로의 통행료

            // 가진 돈보다 통행료가 비싸다면 패스
            if (t > k) continue;

            list.get(s).add(new Pos(d, l, t));
        }

        dijkstra();

        ans = dist[n][0];

        for (int i = 1; i <= k; i++) {
            ans = Math.min(ans, dist[n][i]);
        }

        System.out.println(ans == INF ? -1 : ans);

        br.close();
    }

    private static void dijkstra() {
        PriorityQueue<Pos> pq = new PriorityQueue<>();

        dist = new int[n+1][k+1]; // dp[i][j] => i번째 지점까지 j의 금액으로 간 최소 거리
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dist[i][j] = INF;
            }
        }

        dist[1][0] = 0;
        pq.offer(new Pos(1, 0, 0));

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            // 현재 거리가 now.to까지 now.cost로 간 최소거리보다 크다면 탐색 X
            if (now.dist > dist[now.to][now.cost]) continue;

            for (Pos next : list.get(now.to)) {
                // 가진 돈으로 다음 위치를 통과할 수 없다면 탐색 X
                if (now.cost+next.cost > k) continue;

                if (dist[next.to][now.cost+next.cost] > dist[now.to][now.cost] + next.dist) {
                    dist[next.to][now.cost+next.cost] = dist[now.to][now.cost] + next.dist;
                    pq.offer(new Pos(next.to, dist[next.to][now.cost+next.cost], now.cost+next.cost));
                }
            }
        }
    }
}