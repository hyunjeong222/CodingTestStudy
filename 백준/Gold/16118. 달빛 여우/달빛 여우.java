import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] dist1; // 여우
    static int[][] dist2; // 늑대
    static ArrayList<ArrayList<Node>> list;
    static public class Node implements Comparable<Node> {
        int e; int d; boolean flag;
        public Node(int e, int d, boolean flag) {
            this.e = e; this.d = d; this.flag = flag;
        }
        @Override
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist1 = new int[n+1];
        dist2 = new int[n+1][2]; // 빠르게 이동하는 경우, 느리게 이동하는 경우
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            Arrays.fill(dist1, INF);
            Arrays.fill(dist2[i], INF);
        }

        int u, v, c;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            list.get(u).add(new Node(v, c*2, true));
            list.get(v).add(new Node(u, c*2, true));
        }

        System.out.println(dijkstra());

        br.close();
    }

    private static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 여우
        dist1[1] = 0;
        pq.offer(new Node(1, 0, true));
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist1[now.e] < now.d) continue;

            for (Node next : list.get(now.e)) {
                if (dist1[next.e] > dist1[now.e]+ next.d) {
                    dist1[next.e] = dist1[now.e]+ next.d;
                    pq.offer(new Node(next.e, dist1[next.e], true));
                }
            }
        }

        // 늑대
        dist2[1][1] = 0;
        pq.offer(new Node(1, 0, true));
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist2[now.e][now.flag?1:0] < now.d) continue;

            for (Node next : list.get(now.e)) {
                int nextD = now.d + (now.flag ? next.d/2 : next.d*2);
                if (dist2[next.e][!now.flag?1:0] > nextD) {
                    dist2[next.e][!now.flag?1:0] = nextD;
                    pq.offer(new Node(next.e, dist2[next.e][!now.flag?1:0], !now.flag));
                }
            }
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (dist1[i] < Math.min(dist2[i][0], dist2[i][1])) res++;
        }

        return res;
    }
}