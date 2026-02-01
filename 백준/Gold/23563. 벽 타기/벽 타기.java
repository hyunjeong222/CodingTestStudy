import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int h, w;
    static int sx ,sy, ex, ey;
    static int[][] map, dist;
    static boolean[][] nearWall;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static final int INF = 987654321;
    static public class Node {
        int x; int y;
        public Node(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken()); // 행
        w = Integer.parseInt(st.nextToken()); // 열

        map = new int[h+1][w+1];
        dist = new int[h+1][w+1];
        for (int i = 1; i <= h; i++) {
            Arrays.fill(dist[i], INF);
        }

        for (int i = 1; i <= h; i++) {
            String str = br.readLine();
            for (int j = 1; j <= w; j++) {
                char c = str.charAt(j-1);

                if (c == '#') map[i][j] = -1; // 벽
                else if (c == 'S') { // 시작점
                    sx = i; sy = j;
                } else if (c == 'E') { // 끝점
                    ex = i; ey = j;
                }
            }
        }

        nearWall = new boolean[h+1][w+1];
        checkNearWall();

        bfs();

        // 시작점에서 끝점까지 이동하는 데 걸리는 최소 시간
        System.out.println(dist[ex][ey]);

        br.close();
    }

    private static void checkNearWall() {
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                if (map[i][j] == -1) continue; // 벽

                for (int d = 0; d < 4; d++) {
                    int nx = i+dx[d];
                    int ny = j+dy[d];

                    if (map[nx][ny] == -1) { // 현재 위치는 빈칸 -> 다음 위치에서 벽 타기가 가능
                        nearWall[i][j] = true;
                        break;
                    }
                }

            }
        }
    }

    private static void bfs() {
        Deque<Node> dq = new ArrayDeque<>();
        dq.add(new Node(sx, sy));

        dist[sx][sy] = 0;

        while (!dq.isEmpty()) {
            Node now = dq.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (map[nx][ny] == -1) continue; // 벽

                // 벽 타기 가능 여부
                // 벽에 인접한 칸에서 벽에 인접한 칸으로 이동
                int cost = (nearWall[now.x][now.y] && nearWall[nx][ny]) ? 0 : 1;

                if (dist[nx][ny] > dist[now.x][now.y]+cost) {
                    dist[nx][ny] = dist[now.x][now.y]+cost;

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