import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int h, w;
    static int sx ,sy, ex, ey;
    static int[][] map, dist;
    static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int[] dy = {1, 1, 1, 0, -1, -1, -1, 0};
    static final int INF = 987654321;
    static public class Node {
        int x; int y;
        public Node(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken()); // 가로
        w = Integer.parseInt(st.nextToken()); // 세로

        map = new int[h+1][w+1];
        dist = new int[h+1][w+1];
        for (int i = 1; i <= h; i++) {
            Arrays.fill(dist[i], INF);
        }

        for (int i = 1; i <= h; i++) {
            String str = br.readLine();
            for (int j = 1; j <= w; j++) {
                char c = str.charAt(j-1);

                if (c == '#') map[i][j] = -1; // 암초
                else if (c == 'K') { // 배
                    sx = i; sy = j;
                } else if (c == '*') { // 보물
                    ex = i; ey = j;
                }
            }
        }

        dijkstra();

        // 보물을 찾기까지 소모하는 연료의 최솟값
        // 도달할 수 없는 경우 -1
        System.out.println(dist[ex][ey] == INF ? -1 : dist[ex][ey]);

        br.close();
    }

    private static void dijkstra() {
        Deque<Node> dq = new ArrayDeque<>();
        dq.add(new Node(sx, sy));

        dist[sx][sy] = 0;

        while (!dq.isEmpty()) {
            Node now = dq.poll();

            for (int i = 0; i < 8; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 1 || nx > h || ny < 1 || ny > w) continue;
                if (map[nx][ny] == -1) continue; // 암초

                //(r-1, c+1), (r, c+1), (r+1, c+1) 연료 소모 없이 이동
                // 그 외의 칸 1의 연료 소모
                int cost = (i < 3) ? 0 : 1;
                int nextCost = dist[now.x][now.y] + cost;

                if (dist[nx][ny] > nextCost) {
                    dist[nx][ny] = nextCost;
                    if (cost == 0) { // 가중치 0이면 덱의 앞에 삽입
                        dq.addFirst(new Node(nx, ny));
                    } else { // 가중치가 1이면 덱의 뒤에 삽입
                        dq.addLast(new Node(nx, ny));
                    }
                }
            }
        }
    }
}