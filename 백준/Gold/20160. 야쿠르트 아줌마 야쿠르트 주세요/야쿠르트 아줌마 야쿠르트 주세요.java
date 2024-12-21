import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int v, e;
    static ArrayList<ArrayList<Pos>> list;
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
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

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

        // i번째 지점까지 오는데 소요되는 시간
        long[] yogurtCome = new long[10];
        Arrays.fill(yogurtCome, Long.MAX_VALUE);
        yogurtCome[0] = 0;

        // 야쿠르트 아줌마가 방문하는 순서
        int[] yogurtNode = new int[10];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            yogurtNode[i] = Integer.parseInt(st.nextToken());
        }

        // 이동 가능한 경로가 없다면 그 다음으로 넘어가야 함
        int cur = yogurtNode[0];
        int curIdx = 0;
        for (int i = 1; i < 10; i++) {
            int next = yogurtNode[i];
            int minCost = dijkstra(cur)[next];

            if (minCost == INF) continue; // 이동 가능한 경로 X

            yogurtCome[i] = minCost + yogurtCome[curIdx]; // 이전 방문 시간까지 같이 누적
            cur = next;
            curIdx = i;
        }

        int myStart = Integer.parseInt(br.readLine());
        int[] myDist = dijkstra(myStart);

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            int curNode = yogurtNode[i];
            // 아줌마가 i번째 지점으로 올 수 있고,
            // 내가 더 빨리 도착하는 경우
            if (yogurtCome[i] != Long.MAX_VALUE && myDist[curNode] <= yogurtCome[i]) {
                ans = Math.min(ans, curNode);
            }
        }

        System.out.println(ans == INF ? -1 : ans);
    }

    private static int[] dijkstra(int start) {
        int[] dist = new int[v+1];
        Arrays.fill(dist, INF);

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (dist[now.end] < now.dist) continue;

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