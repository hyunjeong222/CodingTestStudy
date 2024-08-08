import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Pos>> list;
    static int[] dist;
    // i번과 연결된 다른 노드의 회선 정보를 저장, 0이라면 다른 노드와 연결 X 복구 컴퓨터 X
    static int[] connect;
    static boolean[] checked;
    static PriorityQueue<Pos> que;
    static public class Pos implements Comparable<Pos>{
        int end; int dist;
        public Pos(int end, int dist) {
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
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.get(u).add(new Pos(v, w));
            list.get(v).add(new Pos(u, w));
        }

        dist = new int[n+1];
        Arrays.fill(dist, INF);
        connect = new int[n+1];
        checked = new boolean[n+1];

        dijkstra();

        // System.out.println(Arrays.toString(connect));

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (connect[i] == 0) continue;
            cnt++;
            sb.append(connect[i]).append(" ").append(i).append("\n");
        }
        System.out.println(cnt);
        System.out.println(sb.toString());
    }

    private static void dijkstra() {
        que = new PriorityQueue<>();
        que.offer(new Pos(1, 0));
        dist[1] = 0;

        while (!que.isEmpty()) {
            Pos now = que.poll();
            int end = now.end;

            if (dist[end] < now.dist) continue;

            for (Pos next : list.get(end)) {
                if (dist[next.end] > dist[end] + next.dist) {
                    dist[next.end] = dist[end] + next.dist;
                    connect[next.end] = end;
                    que.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }
    }
}