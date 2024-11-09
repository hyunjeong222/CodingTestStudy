import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map, copy;
    static Queue<Pos> que;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos {
        int x; int y; int turn;
        public Pos(int x, int y, int turn) {
            this.x = x; this.y = y;
            this.turn = turn;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1 || map[i][j] == 2) {
                    que.offer(new Pos(i, j, 1));
                }
            }
        }

        bfs();

        int[] ans = new int[4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) ans[1] += 1;
                else if (map[i][j] == 2) ans[2] += 1;
                else if (map[i][j] == 3) ans[3] += 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans[1]).append(" ").append(ans[2]).append(" ").append(ans[3]).append("\n");
        System.out.println(sb.toString());
    }

    private static void bfs() {
        copy = new int[n][m];

        while (!que.isEmpty()) {
            Pos now = que.poll();
            int x = now.x;
            int y = now.y;

            if (map[x][y] == 1 || map[x][y] == 2) {
                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + x;
                    int ny = dy[i] + y;

                   if (rangeCheck(nx, ny)) continue;
                   // -1 : 치료제 가진 마을, 3은 바이러스가 퍼지지 않음
                   if (map[nx][ny] == -1 || map[nx][ny] == 3) continue;

                   if (map[nx][ny] == 0) {
                       map[nx][ny] = map[x][y];
                       copy[nx][ny] = now.turn;
                       que.offer(new Pos(nx, ny, now.turn+1));
                   } else if (copy[nx][ny] == now.turn && map[nx][ny] != map[x][y]) {
                       map[nx][ny] = 3;
                   }
                }
            }
        }
    }

    private static boolean rangeCheck(int x, int y) {
        return  x < 0 || x >= n || y < 0 || y >= m;
    }
}