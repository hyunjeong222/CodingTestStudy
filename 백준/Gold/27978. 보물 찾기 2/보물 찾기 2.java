import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int sx ,sy, ex, ey;
    static int[][] map;
    static int[][] dist;
    static public class Node implements Comparable<Node> {
        int x; int y;
        int d;
        public Node(int x, int y, int d) {
            this.x = x; this.y = y;
            this.d = d;
        }
        @Override
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }
    static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int[] dy = {1, 1, 1, 0, -1, -1, -1, 0};
    static final int MAX = 501;
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= str.length(); j++) {
                char c = str.charAt(j-1);

                if (c == '#') map[i][j] = -1; // 암초
                else if (c == 'K') {
                    sx = i; sy = j;
                } else if (c == '*') {
                    ex = i; ey = j;
                }
            }
        }

        dist = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
        }

        dijkstra();

        System.out.println(dist[ex][ey] == INF ? -1 : dist[ex][ey]);

        br.close();
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(sx, sy, 0));

        dist[sx][sy] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.x][now.y] < now.d) continue;

            for (int i = 0; i < 8; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 1 || nx > n || ny < 1 || ny > m) continue;
                if (map[nx][ny] == -1) continue;

                int nextCost = now.d + (i < 3 ? 0 : 1);

                if (dist[nx][ny] > nextCost) {
                    dist[nx][ny] = nextCost;
                    pq.offer(new Node(nx, ny, nextCost));
                }
            }
        }
    }
}