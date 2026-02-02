import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Edge>> list;
    static int[][] dist; // dist[node][lastType]
    static public class Edge {
        int end;
        int type; // 0: 직류, 1: 교류
        public Edge(int end, int type) {
            this.end = end; this.type = type;
        }
    }
    static class Node {
        int value; int lastType;
        Node(int value, int lastType) {
            this.value = value; this.lastType = lastType;
        }
    }
    static int s, e;
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 지하철 역 수
        m = Integer.parseInt(st.nextToken()); // 선로 수

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int u, v, c;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            list.get(u).add(new Edge(v, c));
            list.get(v).add(new Edge(u, c));
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken()); // 시작 역
        e = Integer.parseInt(st.nextToken()); // 끝 역

        dist = new int[n+1][2];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
        }

        bfs();

        System.out.println(Math.min(dist[e][0], dist[e][1]));

        br.close();
    }

    private static void bfs() {
        Deque<Node> deque = new LinkedList<>();
        deque.offer(new Node(s, 0));
        deque.offer(new Node(s, 1));

        // 아직 급전 방식을 사용하지 않았으므로
        // 두 타입 모두 0으로 시작 가능
        dist[s][0] = 0;
        dist[s][1] = 0;

        while (!deque.isEmpty()) {
            Node now = deque.poll();

            for (Edge next : list.get(now.value)) {
                int nextNode = next.end;
                int nextType = next.type;

                int cost = (now.lastType == nextType) ? 0 : 1;
                int nextDist = dist[now.value][now.lastType]+cost;

                if (dist[nextNode][nextType] > nextDist) {
                    dist[nextNode][nextType] = nextDist;

                    if (cost == 0) deque.offerFirst(new Node(nextNode, nextType));
                    else deque.offerLast(new Node(nextNode, nextType));
                }
            }
        }
    }
}