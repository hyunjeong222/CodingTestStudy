import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int v, e;
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
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken()); // 정점의 개수
        e = Integer.parseInt(st.nextToken()); // 도로의 개수

        list = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }

        int u, v, w;
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            list.get(u).add(new Pos(v, w));
            list.get(v).add(new Pos(u, w));
        }

        // 야쿠르트 아줌마가 방문하는 순서
        int[] yogurtNode = new int[10];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            yogurtNode[i] = Integer.parseInt(st.nextToken());
        }

        // i번째 지점까지 오는데 소요되는 시간
        long[] yogurtCome = new long[10];
        Arrays.fill(yogurtCome, Long.MAX_VALUE);
        yogurtCome[0] = 0;

        int cur = yogurtNode[0];
        int curIdx = 0;
        for (int i = 1; i < 10; i++) {
            int next = yogurtNode[i];
            // 현재 노드에서 다음 노드까지의 최소거리
            int minDist = dijkstra(cur)[next];

            // 이동 경로가 없다면 그 다음 정점으로
            if (minDist == INF) continue;

            // 이전 방문시간까지 같이 누적
            yogurtCome[i] = minDist + yogurtCome[curIdx];

            cur = next;
            curIdx = i;
        }

        int myStart = Integer.parseInt(br.readLine()); // 내가 출발하는 정점 번호
        int[] myDist = dijkstra(myStart);

        int ans = INF;
        for (int i = 0; i < 10; i++) {
            cur = yogurtNode[i];
            if (yogurtCome[i] != Long.MAX_VALUE && myDist[cur] <= yogurtCome[i]) {
                ans = Math.min(ans, cur);
            }
        }

        System.out.println(ans == INF ? -1 : ans);
    }

    private static int[] dijkstra(int cur) {
        int[] dist = new int[v+1];
        Arrays.fill(dist, INF);

        pq = new PriorityQueue<>();
        pq.offer(new Pos(cur, 0));
        dist[cur] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (now.dist > dist[now.end]) continue;

            for (Pos next : list.get(now.end)) {
                if (dist[next.end] > dist[now.end] + next.dist) {
                    dist[next.end] = dist[now.end] + next.dist;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }

        return dist;
    }
}