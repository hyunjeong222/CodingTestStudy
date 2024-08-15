import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][][][] checked;
    static Queue<twoCoins> que;
    static Pos[] coins;
    static public class Pos {
        int x; int y;
        public Pos (int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static public class twoCoins {
        int nx1; int ny1; int nx2; int ny2; int cnt;
        public twoCoins(int nx1, int ny1, int nx2, int ny2, int cnt) {
            this.nx1 = nx1; this.ny1 = ny1; this.nx2 = nx2; this.ny2 = ny2; this.cnt = cnt;
        }
    }
    static int[] dx = {-1, 1, 0, 0}; static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        coins = new Pos[2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (tmp[j] == 'o') coins[idx++] = new Pos(i, j);
                map[i][j] = tmp[j];
            }
        }

        int ans = bfs();
        System.out.println(ans);
    }

    private static int bfs() {
        checked = new boolean[n][m][n][m];
        que = new LinkedList<>();
        que.offer(new twoCoins(coins[0].x, coins[0].y, coins[1].x, coins[1].y, 0));
        checked[coins[0].x][coins[0].y][coins[1].x][coins[1].y] = true;

        while (!que.isEmpty()) {
            twoCoins now = que.poll();

            if (now.cnt >= 10) break;

            for (int i = 0; i < 4; i++) {
                int nx1 = dx[i] + now.nx1;
                int ny1 = dy[i] + now.ny1;
                int nx2 = dx[i] + now.nx2;
                int ny2 = dy[i] + now.ny2;

                // 범위 안에 있고 벽이라면 이동 불가
                if (rangeCheck(nx1, ny1) && map[nx1][ny1] == '#') {
                    nx1 = now.nx1; ny1 = now.ny1;
                }
                if (rangeCheck(nx2, ny2) && map[nx2][ny2] == '#') {
                    nx2 = now.nx2; ny2 = now.ny2;
                }

                int cnt = 0; // 떨어지지 않은 동전 개수
                if (rangeCheck(nx1, ny1)) cnt++;
                if (rangeCheck(nx2, ny2)) cnt++;

                if (cnt == 1) return now.cnt + 1;
                else if (cnt == 2 && !checked[nx1][ny1][nx2][ny2]) {
                    que.offer(new twoCoins(nx1, ny1, nx2, ny2, now.cnt+1));
                    checked[nx1][ny1][nx2][ny2] = true;
                }
            }
        }

        return -1;
    }

    private static boolean rangeCheck(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }
}