import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, j, k;
    static int[] aType, bType;
    static ArrayList<ArrayList<Node>> list;
    static public class Node implements Comparable<Node> {
        int v; int c;
        public Node(int v, int c) {
            this.v = v; this.c = c;
        }
        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 전체 집의 수
        m = Integer.parseInt(st.nextToken()); // 도로의 수

        j = Integer.parseInt(br.readLine()); // 진서의 집

        k = Integer.parseInt(br.readLine()); // 종류별 동물의 수

        aType = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            aType[i] = Integer.parseInt(st.nextToken());
        }
        bType = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            bType[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int u, v, d;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            list.get(u).add(new Node(v, d));
            list.get(v).add(new Node(u, d));
        }

        int[] first = dijkstra(aType);
        int[] second = dijkstra(bType);

        int a = first[j];
        int b = second[j];

        // System.out.println(a);
        // System.out.println(b);

        if (a != INF && b != INF) {
            if (a <= b) {
                System.out.println("A");
                System.out.println(a);
            } else {
                System.out.println("B");
                System.out.println(b);
            }
        } else if (a == INF && b != INF) {
            System.out.println("B");
            System.out.println(b);
        } else if (a != INF && b == INF) {
            System.out.println("A");
            System.out.println(a);
        } else {
            System.out.println(-1);
        }

        br.close();
    }

    private static int[] dijkstra(int[] type) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);

        for (int s : type) {
            dist[s] = 0;
            pq.offer(new Node(s, 0));
        }

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.v] < now.c) continue;

            for (Node next : list.get(now.v)) {
                if (dist[next.v] > dist[now.v]+ next.c) {
                    dist[next.v] = dist[now.v]+ next.c;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }

        return dist;
    }
}